using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LocalRest
{
    class Language
    {
        public string table,ipAdress,deletedTableItems,menu,onCredit,choose,language,save,show,tables;
        public string clearAll;
        public void chooseEnglish()
        {
            table = "Table";
            ipAdress = "IP ADRESS";
            deletedTableItems = "Recycle Bin";
            menu = "MENU";
            onCredit = "ON CREDIT";
            choose = "Choose";
            language = "Language:";
            save = "SAVE";
            show = "SHOW";
            tables = "TABLES";
            clearAll = "CLEAR ALL";
        }
        public void chooseTurkish()
        {
            table = "Masa";
            ipAdress = "IP ADRESİ";
            deletedTableItems = "SİLİNENLER";
            menu = "MENÜ";
            onCredit = "VERESİYE";
            choose = "SEÇ";
            language = "Dil:";
            save = "KAYDET";
            show = "GÖSTER";
            tables = "MASALAR";
            clearAll = "HEPSİNİ SİL";

        }
    }
}
