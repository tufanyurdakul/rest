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
    public partial class deletedItems : Form
    {
        private string ip;
        private string languages;
        Language lang;
        public deletedItems(string url,string language)
        {
            InitializeComponent();
            ip = url;
            languages = language;
           lang  = new Language();
            if (languages == "English")
            {
                lang.chooseEnglish();
            }
            else
            {
                lang.chooseTurkish();
            }
            label1.Text = lang.tables;
            btnShow.Text = lang.show;
            delete.Text = lang.clearAll;
            showComboBox();
            
        }
        public void showComboBox()
        {
            
            
            

            string name = lang.table;
            for(int i=1; i < 16; i++)
            {
                tables.Items.Add(name+"-"+i);
            }
            
        }
        private void delete_Click(object sender, EventArgs e)
        {
            deleteAllPay();
        }
        public async void deleteAllPay()
        {
            HttpClient client = new HttpClient();
          


            var response = await client.GetAsync("http://" + ip + "/deleteDeleted.php");
        }

        private void btnShow_Click(object sender, EventArgs e)
        {

        }
    }
}
