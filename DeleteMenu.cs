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
    public partial class DeleteMenu : Form
    {
        private String Ip,fname,fid,foodid;
        private string[] fids, fnames;
        private Menu menu;
        public DeleteMenu(String Ip,Menu menu)
        {
            this.Ip = Ip;
            InitializeComponent();
            this.menu = menu;
            WebClient wc = new WebClient();


            string xjson = wc.DownloadString("http://" + Ip + "/menu.php");
            var mm = JsonConvert.DeserializeObject(xjson);

            var jArray = JArray.Parse(mm.ToString());
            fnames = new string[jArray.Count];
            fids = new string[jArray.Count];
            // Index the Array and select your CompanyID
            for (int i = 0; i < jArray.Count; i++)
            {
                fname = jArray[i]["foodName"].Value<string>();
                fid = jArray[i]["foodId"].Value<string>();
                fnames[i] = fname;
                fids[i] = fid;
                menuList.Items.Add(fid+"-"+fname);
            }
        }

        private void btnOk_Click(object sender, EventArgs e)
        {
            string x = menuList.Text;
            int position = x.IndexOf("-");
          foodid= x.Substring(0, position);

           
            deleteAll(Ip);
            
        }
        async void deleteAll(string url)
        {
            
            HttpClient client = new HttpClient();
            var values = new Dictionary<string, string>
{
    { "id", foodid },

};

            var content = new FormUrlEncodedContent(values);

            var response = await client.PostAsync("http://" + url + "/deleteMenuItem.php", content);
            menu.addItemOnDb(Ip);
            this.Close();





        }
    }
}
