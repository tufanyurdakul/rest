using System;

using System.Windows.Forms;
using Newtonsoft.Json;
using System.Net;
using Newtonsoft.Json.Linq;

namespace LocalRest
{
    public partial class Menu : Form
    {
      
        private String Ip;
        private string[] fids,fstoks,fnames;
        
        public Menu(string ip)
        {
            InitializeComponent();
            this.Ip = ip;
            addItemOnDb(Ip);
            // r(ip);
        }
        
        private void button1_Click(object sender, EventArgs e)
        {
            addFood add = new addFood(Ip,this);
            
            add.Show();
        }
     public  void addItemOnDb(string url)
        {
          
             WebClient wc = new WebClient();
        
          
       string xjson = wc.DownloadString("http://"+url+"/menu.php");
         var mm= JsonConvert.DeserializeObject(xjson);
            var jArray = JArray.Parse(mm.ToString());
           fids = new string[jArray.Count];
             fstoks = new string[jArray.Count];
            fnames = new string[jArray.Count];
            for (int i = 0; i < jArray.Count; i++)
            {
              string  fid = jArray[i]["foodId"].Value<string>();
              string  fstok = jArray[i]["Stok"].Value<string>();
                string fname = jArray[i]["foodName"].Value<string>();
                fstoks[i] = fstok;
                fids[i] = fid;
                fnames[i] = fname;
            }
            dataGridView1.DataSource = mm;


        }

        private void button3_Click(object sender, EventArgs e)
        {
            addItemOnDb(Ip);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            DeleteMenu dm = new DeleteMenu(Ip,this);
            
            dm.Show();
        }

        private void addStock_Click(object sender, EventArgs e)
        {
            addStock add = new addStock(this, Ip,fids,fstoks,fnames);
            add.Show();
        }
    }
}
