using System;

using System.IO;

using System.Text;

using System.Windows.Forms;





namespace LocalRest
{
    public partial class Form1 : Form
    {
        
        private String Ip,choosenLanguage;
        string path = Environment.GetFolderPath(Environment.SpecialFolder.Desktop) + @"\t.txt";
        string path2 = Environment.GetFolderPath(Environment.SpecialFolder.Desktop) + @"\language.txt";
        public Form1()
        {
            InitializeComponent();
            cmbLanguage.Items.Add("Türkçe");
            cmbLanguage.Items.Add("English");
            getIpAdress();
            choosenLanguages();
            

        }
        public void getIpAdress()
        {
            if (File.Exists(path))
            {
                var fileStream = new FileStream(path, FileMode.Open, FileAccess.Read);
                using (var streamReader = new StreamReader(fileStream, Encoding.UTF8))
                {
                    Ip = streamReader.ReadToEnd();
                }
            }
        }
        public void choosenLanguages()
        {
            if (File.Exists(path2))
            {
                var fileStream = new FileStream(path2, FileMode.Open, FileAccess.Read);
                using (var streamReader = new StreamReader(fileStream, Encoding.UTF8))
                {
                    choosenLanguage = streamReader.ReadToEnd();
                    selectedLanguage(choosenLanguage);
                }
            }
            if (!File.Exists(path2))
            {

                choosenLanguage = "Türkçe";
                
            }
        }

        private void selectedLanguage(string choosenLanguage)
        {
            Language language = new Language();
            if (choosenLanguage == "English")
            {
                cmbLanguage.Text = "English";
                language.chooseEnglish();
                
            }
            else
            {
                cmbLanguage.Text = "Türkçe";
                language.chooseTurkish();
            }
            t1.Text = language.table + "-1";
            t2.Text = language.table + "-2";
            t3.Text = language.table + "-3";
            t4.Text = language.table + "-4";
            t5.Text = language.table + "-5";
            t6.Text = language.table + "-6";
            t7.Text = language.table + "-7";
            t8.Text = language.table + "-8";
            t9.Text = language.table + "-9";
            t10.Text = language.table + "-10";
            t11.Text = language.table + "-11";
            t12.Text = language.table + "-12";
            t13.Text = language.table + "-13";
            t14.Text = language.table + "-14";
            t15.Text = language.table + "-15";
            ip.Text = language.ipAdress;
            button1.Text = language.menu;
            button3.Text = language.onCredit;
            recycleBin.Text = language.deletedTableItems;
            button4.Text = language.choose;
            label1.Text = language.language;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Menu menu = new Menu(Ip);
            menu.Show();
        }

        private void t1_Click(object sender, EventArgs e)
        {
            sT(Ip, "1");
            
        }

        private void t2_Click(object sender, EventArgs e)
        {
            sT(Ip, "2");
        }

        private void t3_Click(object sender, EventArgs e)
        {
            sT(Ip, "3");
        }

        private void t4_Click(object sender, EventArgs e)
        {
            sT(Ip, "4");
        }

        private void t5_Click(object sender, EventArgs e)
        {
            sT(Ip, "5");
        }

        private void t6_Click(object sender, EventArgs e)
        {
            sT(Ip, "6");
        }

        private void t7_Click(object sender, EventArgs e)
        {
            sT(Ip, "7");
        }

        private void t8_Click(object sender, EventArgs e)
        {
            sT(Ip, "8");
        }

        private void t9_Click(object sender, EventArgs e)
        {
            sT(Ip, "9");
        }

        private void t10_Click(object sender, EventArgs e)
        {
            sT(Ip, "10");
        }

        private void t11_Click(object sender, EventArgs e)
        {
            sT(Ip, "11");
        }

        private void t12_Click(object sender, EventArgs e)
        {
            sT(Ip, "12");
        }

        private void t13_Click(object sender, EventArgs e)
        {
            sT(Ip, "13");
        }

        private void t14_Click(object sender, EventArgs e)
        {
            sT(Ip, "14");
        }

        private void t15_Click(object sender, EventArgs e)
        {
            sT(Ip, "15");
        }
        public void sT(string ip,string tid)
        {
            showTable st = new showTable(ip, tid);
            st.Show();
        }

        private void ip_Click(object sender, EventArgs e)
        {
            IpAdress adress = new IpAdress(choosenLanguage,this);
            adress.Show();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            credit c = new credit(Ip);
            c.Show();
        }

        private void recycleBin_Click(object sender, EventArgs e)
        {
            deletedItems items = new deletedItems(Ip,choosenLanguage);
            items.Show();
        }

        private void button4_Click(object sender, EventArgs e)
        {
           string languageofProgram = cmbLanguage.Text;
            if (!File.Exists(path))
            {
                // Create a file to write to.
                using (StreamWriter sw = File.CreateText(path2))
                {
                    sw.Write(languageofProgram);
                    
                }
            }
            else
            {




                File.WriteAllText(path2, languageofProgram);
            }
            choosenLanguages();
        }
    }
}
