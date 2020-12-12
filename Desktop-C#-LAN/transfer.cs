using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace LocalRest
{
    public partial class transfer : Form
    {
        private string[] foodIds,amounts,foodName;
        private string ip,peopleName;
        private showTable table,table2;
        public transfer(string url,string[] foodIds,string[] amounts,string[] foodName,showTable table)
        {
            InitializeComponent();
            ip = url;
            this.foodIds = foodIds;
            this.amounts = amounts;
            this.foodName = foodName;
            this.table = table;
            this.table2 = table;
            showPeople();
            
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string people = cmbPeople.Text;
            int peopleIdLastIndex = people.IndexOf("-");
            string peopleId = people.Substring(0, peopleIdLastIndex);
            peopleName = people.Substring(peopleIdLastIndex, people.Length - peopleIdLastIndex);
            for(int i=0; i<foodIds.Length; i++)
            {
                transferFoods(peopleId,foodIds[i],amounts[i],foodName[i]);
            }
            string message ="Masa Hesabı Silinsin Mi?";
            DialogResult result = MessageBox.Show(message,"Deleting", MessageBoxButtons.YesNo);
            if (result == System.Windows.Forms.DialogResult.Yes)
            {
                this.Close();
                table.deleteAll(ip);
                table2.addItemOnDb(ip);
                
                
            }

        }
        public  async void transferFoods(string id,string foodId,string amount,string foodName)
        {
            
            HttpClient client = new HttpClient();
            var values = new Dictionary<string, string>
{
    { "personId", id },
    { "foodId", foodId },
    { "amount", amount },

};

            var content = new FormUrlEncodedContent(values);

            var response = await client.PostAsync("http://" + ip + "/addCredit.php", content);

            var responseString = await response.Content.ReadAsStringAsync();
            if( response.StatusCode == HttpStatusCode.OK)
            {
                string message = peopleName + " "+ "adlı kişiye "+foodName+" borcu eklendi.";
                MessageBox.Show(message);
            }
        }
        async void showPeople()
        {
           
            HttpClient client = new HttpClient();
  

            

            var response = await client.GetAsync("http://" + ip + "/people.php");

            var responseString = await response.Content.ReadAsStringAsync();

            var mm = JsonConvert.DeserializeObject(responseString);

            var jArray = JArray.Parse(mm.ToString());
         for(int i=0; i<jArray.Count; i++)
            {
                cmbPeople.Items.Add(jArray[i]["personId"].Value<string>() + "-" + jArray[i]["personName"].Value<string>() + " " + jArray[i]["personSurname"].Value<string>());
            }
         



        }
    }
}
