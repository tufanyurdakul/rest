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
    public partial class credit : Form
    {
        private string ip;
        private int last;
        private bool isFirst;
        public credit(string url)
        {
            InitializeComponent();
            ip = url;
            showPerson(url);
        }
        public void clear(string name)
        {
            for (int i = 0; i < comboBox1.Items.Count; i++)
            {
                string value = comboBox1.GetItemText(comboBox1.Items[i]);
                int x=value.IndexOf("-");
                string thisname=value.Substring(0, x);
                if (name == thisname)
                {
                    comboBox1.Items.RemoveAt(i);
                }
            }
        }
        public void showPerson(string url)
        {
            comboBox1.Refresh();
            WebClient wc = new WebClient();


            string xjson = wc.DownloadString("http://" + url + "/people.php");
            var mm = JsonConvert.DeserializeObject(xjson);

            var jArray = JArray.Parse(mm.ToString());

            int x = jArray.Count-1;
            
         string[]   pnames = new string[jArray.Count];
            string[] psnames = new string[jArray.Count];
            string[]   pids = new string[jArray.Count];
            
            // Index the Array and select your CompanyID
            for (int i = 0; i < jArray.Count; i++)
            {
             
              string  pname = jArray[i]["personName"].Value<string>();
                string psname = jArray[i]["personSurname"].Value<string>();
                string  pid = jArray[i]["personId"].Value<string>();
                pnames[i] = pname;
                pids[i] = pid;
                psnames[i] = psname;
                
                comboBox1.Items.Add(pid+"-"+pname+" "+psname);
                
            }
            if (x >= 0)
            {
                isFirst = false;
                string lastis = pids[x];
                last = Int32.Parse(lastis);
                
            }
            else
            {
                isFirst = true;
                string message = "Lütfen Kişi Ekleyiniz.";
                DialogResult result = MessageBox.Show(message);
            }
            
            

        }
        private void add_Click(object sender, EventArgs e)
        {
            string name = textBox1.Text;
            string surname = textBox2.Text;
            addItemOnDb(ip, name, surname);
            
           
        }
        async void addItemOnDb(string url,string name,string sname)
        {
            HttpClient client = new HttpClient();
            var values = new Dictionary<string, string>
{
    { "personName", name },
    { "personSurname", sname }
};

            var content = new FormUrlEncodedContent(values);

            var response = await client.PostAsync("http://" + url + "/addPerson.php", content);
            if (response.StatusCode == HttpStatusCode.OK)
            {

                string message =name+" "+sname+"  "+"adlı kişi eklendi." ;
                MessageBox.Show(message);
                if (isFirst)
                {
                    showPerson(ip);
                }
                else
                {
                    last += 1;
                    comboBox1.Items.Add(last + "-" + name + " " + sname);
                }
                


            }
            else
            {
                string message = name + " " + sname + "  " + "adlı kişi eklenemedi.";
                MessageBox.Show(message);

            }
            

        }

        private void show_Click(object sender, EventArgs e)
        {
            if (comboBox1.Text != string.Empty) {
                string f = comboBox1.Text;
                int position = f.IndexOf("-");

                string cid = f.Substring(0, position);

                string cname = f.Substring(position, f.Length - position);
                showCredits sc = new showCredits(ip, cid, cname.Replace("-", ""), this);
                sc.Show();
            }
            else
            {
                string message ="Lütfen İsim Seçiniz";
                DialogResult result = MessageBox.Show(message);
            }
            
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void credit_Load(object sender, EventArgs e)
        {

        }
    }
}
