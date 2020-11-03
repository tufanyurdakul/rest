using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace LocalRest
{
    public partial class showCredits : Form
    {
        private string ip,id,fullName;
        credit cred;

        private void add_Click(object sender, EventArgs e)
        {
            addCredit addition = new addCredit(id, ip,this);
            addition.Show();
        }

        private void delete_Click(object sender, EventArgs e)
        {

        }

        private void pay_Click(object sender, EventArgs e)
        {
            string message = "Veresiye Hesabı Silinsin Mi?";
            DialogResult result = MessageBox.Show(message, "Deleting", MessageBoxButtons.YesNo);
            if (result == System.Windows.Forms.DialogResult.Yes)
            {
                deleteAllPay();
                showCredit();
                

            }
            dataGridView1.Refresh();
        }

        public showCredits(string url,string id,string fullName,credit cred)
        {
            InitializeComponent();
            ip = url;
            this.id = id;
            this.fullName = fullName;
            lblName.Text = fullName;
            this.cred = cred;
            showCredit();
        }

        private void tlpay_Click(object sender, EventArgs e)
        {
            string paying = txtPay.Text;
            addPay(paying);
            showCredit();
            dataGridView1.Refresh();
            lblPay.Refresh();
            lblTotal.Refresh();
        }

        private void showCredits_Load(object sender, EventArgs e)
        {

        }
        public async void addPay(string paying)
        {
            HttpClient client = new HttpClient();
            var values = new Dictionary<string, string>
{
    { "personId", id },
     { "payValue", paying },
};

            var content = new FormUrlEncodedContent(values);

            var response = await client.PostAsync("http://" + ip + "/addPay.php", content);
        }

        private void DeletePerson_Click(object sender, EventArgs e)
        {
            string message = fullName+"Adlı Kişi Silinsin Mi?";
            DialogResult result = MessageBox.Show(message, "Deleting", MessageBoxButtons.YesNo);
            if (result == System.Windows.Forms.DialogResult.Yes)
            {

                deletePerson();
                showCredit();
                cred.clear(id);
               
             
                
                this.Close();


            }
            dataGridView1.Refresh();
        }

        public async void deleteAllPay()
        {
            HttpClient client = new HttpClient();
            var values = new Dictionary<string, string>
{
    { "personId", id },
     
};

            var content = new FormUrlEncodedContent(values);

            var response = await client.PostAsync("http://" + ip + "/deleteCredit.php", content);
        }

        public async void deletePerson()
        {
            HttpClient client = new HttpClient();
            var values = new Dictionary<string, string>
{
    { "personId", id },

};

            var content = new FormUrlEncodedContent(values);

            var response = await client.PostAsync("http://" + ip + "/deletePerson.php", content);
        }
        public async void showCredit()
        {
            HttpClient client = new HttpClient();
            var values = new Dictionary<string, string>
{
    { "personId", id },

};
            var values2 = new Dictionary<string, string>
{
    { "personId", id },

};

            var content = new FormUrlEncodedContent(values);
            var content2 = new FormUrlEncodedContent(values2);

            var response = await client.PostAsync("http://" + ip + "/credits.php", content);
            var response2= await client.PostAsync("http://" + ip + "/pay.php", content2);
            var responseString = await response.Content.ReadAsStringAsync();
            var responseString2 = await response2.Content.ReadAsStringAsync();
            var mm = JsonConvert.DeserializeObject(responseString);
            var mm2 = JsonConvert.DeserializeObject(responseString2);
            var jArray = JArray.Parse(mm.ToString());
            var jArray2 = JArray.Parse(mm2.ToString());
            double total=0;
            double payed = 0;
            double sum = 0;
            for (int i = 0; i < jArray.Count; i++)
            {
                
                total = total + Convert.ToDouble(jArray[i]["amount"].Value<string>()) * Convert.ToDouble(jArray[i]["foodPrice"].Value<string>());
            }
            value.Text = total.ToString()+"TL";

            for(int i=0; i<jArray2.Count; i++)
            {
                if (jArray2[i]["payValue"].Value<string>()!=string.Empty)
                {
                    payed = payed + Convert.ToDouble(jArray2[i]["payValue"].Value<string>());
                }
                else
                {
                    payed = payed + 0;
                }
            }
            lblPay.Text = payed.ToString() + "TL";
            sum = total - payed;
            lblTotal.Text = sum.ToString() + "TL";

            dataGridView1.DataSource = mm;
            dataGridView1.Refresh();
        }
    }
}
