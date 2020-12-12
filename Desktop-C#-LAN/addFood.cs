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

    public partial class addFood : Form
    {
        private string IP,fName,fPrice,fstok;
        private Menu menu;
        public addFood(String IP,Menu menu)
        {
            InitializeComponent();
            this.IP = IP;
            this.menu = menu;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            fName = foodName.Text;
            fPrice = price.Text;
            fstok = stok.Text;
            if (fName == string.Empty && fPrice == string.Empty)
            {

            }
            else
            {
                addItemOnDb(IP);
            }
        }
        async void addItemOnDb(string url)
        {
            HttpClient client = new HttpClient();
            var values = new Dictionary<string, string>
{
    { "foodName", fName },
    { "foodPrice", fPrice },
    { "stok", fstok }
};

            var content = new FormUrlEncodedContent(values);

            var response = await client.PostAsync("http://" + url + "/index.php", content);

            var responseString = await response.Content.ReadAsStringAsync();


            menu.addItemOnDb(IP);
            this.Close();

        }
    }
}
