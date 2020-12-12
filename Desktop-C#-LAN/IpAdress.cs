using System;

using System.IO;
using System.Text;
using System.Windows.Forms;

namespace LocalRest
{
    public partial class IpAdress : Form
    {
        private Form1 form;
        string path = Environment.GetFolderPath(Environment.SpecialFolder.Desktop) + @"\t.txt";
        
        public IpAdress(string choosenLanguage,Form1 form)
           
        {
            InitializeComponent();
            this.form = form;
            choosenLanguages(choosenLanguage);
            if (File.Exists(path))
            {
                var fileStream = new FileStream(path, FileMode.Open, FileAccess.Read);
                using (var streamReader = new StreamReader(fileStream, Encoding.UTF8))
                {
                    string oldIp = streamReader.ReadToEnd();
                    txtIp.Text = oldIp;
                }
            }
        }
        public void choosenLanguages(string language)
        {
            Language lang = new Language();
            if (language == "English")
            {
                lang.chooseEnglish();

            }
            else
            {
                lang.chooseTurkish();
            }
            button1.Text = lang.save;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string ip = txtIp.Text;
          
            if (!File.Exists(path))
            {
                // Create a file to write to.
                using (StreamWriter sw = File.CreateText(path))
                {
                    sw.Write(ip);
                    
                }
            }
            else
            {

               


                File.WriteAllText(path, ip);
            }
            form.getIpAdress();
            
        }

        
    }
}
