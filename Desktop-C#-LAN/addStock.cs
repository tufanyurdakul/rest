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
    public partial class addStock : Form
    {
        private string ip;
        private Menu menu;
        private string[] fids, fstocks,foodnames;
        public addStock(Menu menu,string url,string[] fid,string[] fstok,string[] foodname)
        {
            InitializeComponent();
            ip = url;
            this.menu = menu;
            fids = fid;
            fstocks = fstok;
            foodnames = foodname;
            for(int i=0;i<fstocks.Length; i++)
            {
                comboBox1.Items.Add(fids[i]+"-"+foodnames[i]);
            }
        }

        private async void add_Click(object sender, EventArgs e)
        {
            int choosenStock = Int32.Parse(textBox1.Text);
            int choosenIndex = comboBox1.SelectedIndex;
            int myStock = Int32.Parse(fstocks[choosenIndex]);
            string myId = fids[choosenIndex];
            int newStock = myStock + choosenStock;
            HttpClient client = new HttpClient();
            var values3 = new Dictionary<string, string>
{
    { "foodId",myId  },
    { "stok",newStock.ToString() },

};
            var content3 = new FormUrlEncodedContent(values3);
            var response3 = await client.PostAsync("http://" + ip + "/updateStok.php", content3);
            menu.addItemOnDb(ip);
        }
    }
}
