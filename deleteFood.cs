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
    public partial class deleteFood : Form
    {
        private int[] id;
        private string ip;
        private showTable table;
        public deleteFood(int[] ids,string url,showTable table)
        {
            InitializeComponent();
            id = new int[ids.Length];
            id = ids;
            ip = url;
            this.table = table;
            for(int i=0; i<id.Length; i++)
            {
                //id[i] = ids[i];
                comboBox1.Items.Add(id[i]);
            }

        }

        private void delete_Click(object sender, EventArgs e)
        {
            string choosenId = comboBox1.Text;
            deleteItem(ip, choosenId);
            table.addItemOnDb(ip);

        }
        async void deleteItem(string url,string cid)
        {

            HttpClient client = new HttpClient();
            var values = new Dictionary<string, string>
{
    { "id", cid},

};

            var content = new FormUrlEncodedContent(values);

            var response = await client.PostAsync("http://" + url + "/deleteFood.php", content);

            this.Close();





        }
    }
}
