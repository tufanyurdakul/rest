namespace LocalRest
{
    partial class showTable
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.label1 = new System.Windows.Forms.Label();
            this.lblPrice = new System.Windows.Forms.Label();
            this.button1 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.button3 = new System.Windows.Forms.Button();
            this.lblName = new System.Windows.Forms.Label();
            this.edit = new System.Windows.Forms.Button();
            this.veresiyeAktar = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Location = new System.Drawing.Point(2, 3);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.Size = new System.Drawing.Size(410, 337);
            this.dataGridView1.TabIndex = 0;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(419, 13);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(54, 13);
            this.label1.TabIndex = 1;
            this.label1.Text = "TOPLAM:";
            // 
            // lblPrice
            // 
            this.lblPrice.AutoSize = true;
            this.lblPrice.Location = new System.Drawing.Point(479, 13);
            this.lblPrice.Name = "lblPrice";
            this.lblPrice.Size = new System.Drawing.Size(13, 13);
            this.lblPrice.TabIndex = 2;
            this.lblPrice.Text = "0";
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(445, 97);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 3;
            this.button1.Text = "EKLE";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(445, 126);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(75, 23);
            this.button2.TabIndex = 4;
            this.button2.Text = "SİL";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // button3
            // 
            this.button3.Location = new System.Drawing.Point(445, 155);
            this.button3.Name = "button3";
            this.button3.Size = new System.Drawing.Size(75, 23);
            this.button3.TabIndex = 5;
            this.button3.Text = "ÖDENDİ";
            this.button3.UseVisualStyleBackColor = true;
            this.button3.Click += new System.EventHandler(this.button3_Click);
            // 
            // lblName
            // 
            this.lblName.AutoSize = true;
            this.lblName.Location = new System.Drawing.Point(445, 51);
            this.lblName.Name = "lblName";
            this.lblName.Size = new System.Drawing.Size(13, 13);
            this.lblName.TabIndex = 6;
            this.lblName.Text = "0";
            // 
            // edit
            // 
            this.edit.Location = new System.Drawing.Point(445, 184);
            this.edit.Name = "edit";
            this.edit.Size = new System.Drawing.Size(75, 23);
            this.edit.TabIndex = 7;
            this.edit.Text = "DÜZENLE";
            this.edit.UseVisualStyleBackColor = true;
            this.edit.Click += new System.EventHandler(this.edit_Click);
            // 
            // veresiyeAktar
            // 
            this.veresiyeAktar.Location = new System.Drawing.Point(445, 214);
            this.veresiyeAktar.Name = "veresiyeAktar";
            this.veresiyeAktar.Size = new System.Drawing.Size(75, 23);
            this.veresiyeAktar.TabIndex = 8;
            this.veresiyeAktar.Text = "V.AKTAR";
            this.veresiyeAktar.UseVisualStyleBackColor = true;
            this.veresiyeAktar.Click += new System.EventHandler(this.veresiyeAktar_Click);
            // 
            // showTable
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(558, 343);
            this.Controls.Add(this.veresiyeAktar);
            this.Controls.Add(this.edit);
            this.Controls.Add(this.lblName);
            this.Controls.Add(this.button3);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.lblPrice);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.dataGridView1);
            this.Name = "showTable";
            this.Text = "showTable";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label lblPrice;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Button button3;
        private System.Windows.Forms.Label lblName;
        private System.Windows.Forms.Button edit;
        private System.Windows.Forms.Button veresiyeAktar;
    }
}