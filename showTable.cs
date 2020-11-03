using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Windows.Forms;

namespace LocalRest
{
    public partial class showTable : Form
    {
        private string id, ip;
        private double total;
        private int[] ids;
        private string[] foodIds,amounts,foodName;
        public showTable(string ip,string id)
        {
            this.id = id;
            this.ip = ip;
           
           InitializeComponent();
            lblName.Text ="Masa-"+ id;
            addItemOnDb(ip);

        }

       
        public async void addItemOnDb(string url)
        {
            total = 0;
            HttpClient client = new HttpClient();
            var values = new Dictionary<string, string>
{
    { "masaNumarasi", id },
 
};

            var content = new FormUrlEncodedContent(values);

            var response = await client.PostAsync("http://" + url + "/tables.php", content);

            var responseString = await response.Content.ReadAsStringAsync();
            var mm = JsonConvert.DeserializeObject(responseString);


            dataGridView1.DataSource = mm;
            
            var jArray = JArray.Parse(mm.ToString());
            ids = new int[jArray.Count];
            foodIds = new string[jArray.Count];
            amounts = new string[jArray.Count];
            foodName = new string[jArray.Count];
            for (int i = 0; i < jArray.Count; i++)
            {
                ids[i] = Int32.Parse(jArray[i]["id"].Value<string>());
               foodIds[i]= jArray[i]["foodId"].Value<string>();
                amounts[i] = jArray[i]["amount"].Value<string>();
                foodName[i] = jArray[i]["foodName"].Value<string>();
                total = total + Convert.ToDouble(jArray[i]["amount"].Value<string>())*Convert.ToDouble(jArray[i]["foodPrice"].Value<string>());
            }
            lblPrice.Text = total.ToString();
            dataGridView1.Update();
            dataGridView1.Refresh();

        }
    public    async void deleteAll(string url)
        {

            total = 0;
            HttpClient client = new HttpClient();
            var values = new Dictionary<string, string>
{
    { "id", id },

};

            var content = new FormUrlEncodedContent(values);

            var response = await client.PostAsync("http://" + url + "/deleteAll.php", content);

            var responseString = await response.Content.ReadAsStringAsync();
           


           

        }
        public async void deletedItems(string url,string foodId,string amount )
        {
            string time = DateTime.Now.ToString("yyyy-MM-dd h:mm tt");
            total = 0;
            HttpClient client = new HttpClient();
            var values = new Dictionary<string, string>
{
    { "foodId", foodId },
    { "masaNumarasi", id },
    { "amount", amount },
    { "date", time },

};

            var content = new FormUrlEncodedContent(values);

            var response = await client.PostAsync("http://" + url + "/addDeletedItems.php", content);

            var responseString = await response.Content.ReadAsStringAsync();

            



        }

        private void button3_Click(object sender, EventArgs e)
        {
            string message = "Masa Hesabı Silinsin Mi?";
            DialogResult result = MessageBox.Show(message, "Deleting", MessageBoxButtons.YesNo);
            if (result == System.Windows.Forms.DialogResult.Yes)
            {
               
                for (int i = 0; i < foodIds.Length; i++) {
                    deletedItems(ip,foodIds[i],amounts[i]);
                }
                deleteAll(ip);
               
               

            }
            addItemOnDb(ip);
            

        }

        private void edit_Click(object sender, EventArgs e)
        {

        }

        private void veresiyeAktar_Click(object sender, EventArgs e)
        {
            transfer t = new transfer(ip, foodIds, amounts,foodName,this);
            t.Show();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            deleteFood df = new deleteFood(ids,ip,this);
            df.Show();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            addTable add = new addTable(id, ip,this);
            add.Show();
        }
        
    }
}
