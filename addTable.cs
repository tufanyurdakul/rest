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
    public partial class addTable : Form
    {
        private string id, ip,fname,fid,foodid;
        private string[] fids, fnames;
        private readonly showTable frm1;
        private string foodnameofcmb;
      

        public addTable(string id , string ip,showTable t)
        {
            InitializeComponent();
            this.id = id;
            this.ip = ip;
            frm1 = t;
            WebClient wc = new WebClient();


            string xjson = wc.DownloadString("http://" + ip + "/menu.php");
            var mm = JsonConvert.DeserializeObject(xjson);

            var jArray = JArray.Parse(mm.ToString());
            fnames = new string[jArray.Count];
            fids = new string[jArray.Count];
            // Index the Array and select your CompanyID
            for (int i=0; i < jArray.Count; i++)
            {
              fname = jArray[i]["foodName"].Value<string>();
               fid = jArray[i]["foodId"].Value<string>();
                fnames[i] = fname;
                fids[i] = fid;
                cmbFoods.Items.Add(fname);
            }
            
           // dataGridView1.DataSource = mm;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            foodnameofcmb=cmbFoods.Text;
           string amount = txtAmount.Text;
            for(int i=0; i < fnames.Length; i++)
            {
                if (foodnameofcmb == fnames[i])
                {
                    foodid = fids[i];
                }
            }
            addItemOnDb(foodid,amount);

        }
        async void addItemOnDb(string fid,string amount)
        {
            HttpClient client = new HttpClient();
            var values = new Dictionary<string, string>
{
    { "foodId",fid  },
    { "masaNumarasi", id },
    { "amount", amount }
};
            var values2 = new Dictionary<string, string>
{
    { "foodId",fid  },
    
};
            var content = new FormUrlEncodedContent(values);
            var content2 = new FormUrlEncodedContent(values2);

            var response = await client.PostAsync("http://" + ip + "/addFood.php", content);
            var response2 = await client.PostAsync("http://" + ip + "/decreaseStok.php", content2);
            var responseString = await response.Content.ReadAsStringAsync();
            var responseString2 = await response2.Content.ReadAsStringAsync();
            var mm = JsonConvert.DeserializeObject(responseString2);

            var jArray = JArray.Parse(mm.ToString());
            string s = jArray[0]["stok"].Value<string>();
            int stok = Int32.Parse(s);
           
            if (stok > 0)
            {
                int totalStok = stok - Int32.Parse(amount);
                if (totalStok < 0)
                {
                    totalStok = 0;
                }
                string ts = totalStok.ToString();
                var values3 = new Dictionary<string, string>
{
    { "foodId",fid  },
    { "stok",ts },

};
                var content3 = new FormUrlEncodedContent(values3);
                var response3 = await client.PostAsync("http://" + ip + "/updateStok.php", content3);
                if (response3.StatusCode == HttpStatusCode.OK)
                {
                    string message = "Stok Güncellendi";
                    DialogResult result = MessageBox.Show(message);
                }
               
            }
            if (response.StatusCode == HttpStatusCode.OK)
            {
                string message = amount+" adet "+foodnameofcmb+" eklendi.";
                DialogResult result = MessageBox.Show(message);
                frm1.addItemOnDb(ip);
                this.Close();
            }



        }
    }
}
