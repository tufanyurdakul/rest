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
    public partial class addCredit : Form
    {
        private showCredits credit;
        private string id, ip;
        public addCredit(string id, string ip,showCredits credit)
        {
            InitializeComponent();
            this.id = id;
            this.ip = ip;
            this.credit = credit;
            showFoods();
        }

        private void choose_Click(object sender, EventArgs e)
        {
            string choosen = comboBox1.Text;
            int index=  choosen.IndexOf("-");
            string idOfChoosen = choosen.Substring(0, index);
            string amount = textBox1.Text;
            addCredits(idOfChoosen,amount);
        }
        public void showFoods()
        {
            WebClient wc = new WebClient();


            string xjson = wc.DownloadString("http://" + ip + "/menu.php");
            var mm = JsonConvert.DeserializeObject(xjson);
            var jArray = JArray.Parse(mm.ToString());
            for (int i = 0; i < jArray.Count; i++)
            {
                comboBox1.Items.Add(jArray[i]["foodId"].Value<string>() + "-" + jArray[i]["foodName"].Value<string>());

            }
        }
        public async void addCredits(string fid,string amount)
        {
            HttpClient client = new HttpClient();
            var values = new Dictionary<string, string>
{
    { "personId", id },
    { "foodId", fid },
    { "amount", amount }
};

            var content = new FormUrlEncodedContent(values);

            var response = await client.PostAsync("http://" + ip + "/addCredit.php", content);


            if (response.StatusCode == HttpStatusCode.OK)
            {
                credit.showCredit();
            }
        }
    }
}