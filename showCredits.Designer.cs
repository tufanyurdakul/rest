namespace LocalRest
{
    partial class showCredits
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
            this.lblName = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.add = new System.Windows.Forms.Button();
            this.delete = new System.Windows.Forms.Button();
            this.pay = new System.Windows.Forms.Button();
            this.txt = new System.Windows.Forms.Label();
            this.value = new System.Windows.Forms.Label();
            this.tlpay = new System.Windows.Forms.Button();
            this.label2 = new System.Windows.Forms.Label();
            this.lblPay = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.lblTotal = new System.Windows.Forms.Label();
            this.txtPay = new System.Windows.Forms.TextBox();
            this.DeletePerson = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Location = new System.Drawing.Point(-1, 42);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.Size = new System.Drawing.Size(467, 291);
            this.dataGridView1.TabIndex = 0;
            // 
            // lblName
            // 
            this.lblName.AutoSize = true;
            this.lblName.Location = new System.Drawing.Point(78, 9);
            this.lblName.Name = "lblName";
            this.lblName.Size = new System.Drawing.Size(0, 13);
            this.lblName.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(66, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "İsim Soyisim:";
            // 
            // add
            // 
            this.add.Location = new System.Drawing.Point(489, 160);
            this.add.Name = "add";
            this.add.Size = new System.Drawing.Size(75, 23);
            this.add.TabIndex = 3;
            this.add.Text = "EKLE";
            this.add.UseVisualStyleBackColor = true;
            this.add.Click += new System.EventHandler(this.add_Click);
            // 
            // delete
            // 
            this.delete.Location = new System.Drawing.Point(489, 199);
            this.delete.Name = "delete";
            this.delete.Size = new System.Drawing.Size(75, 23);
            this.delete.TabIndex = 4;
            this.delete.Text = "SİL";
            this.delete.UseVisualStyleBackColor = true;
            this.delete.Click += new System.EventHandler(this.delete_Click);
            // 
            // pay
            // 
            this.pay.Location = new System.Drawing.Point(489, 283);
            this.pay.Name = "pay";
            this.pay.Size = new System.Drawing.Size(75, 23);
            this.pay.TabIndex = 5;
            this.pay.Text = "ÖDENDİ";
            this.pay.UseVisualStyleBackColor = true;
            this.pay.Click += new System.EventHandler(this.pay_Click);
            // 
            // txt
            // 
            this.txt.AutoSize = true;
            this.txt.Location = new System.Drawing.Point(486, 51);
            this.txt.Name = "txt";
            this.txt.Size = new System.Drawing.Size(35, 13);
            this.txt.TabIndex = 6;
            this.txt.Text = "Tutar:";
            // 
            // value
            // 
            this.value.AutoSize = true;
            this.value.Location = new System.Drawing.Point(527, 51);
            this.value.Name = "value";
            this.value.Size = new System.Drawing.Size(0, 13);
            this.value.TabIndex = 7;
            // 
            // tlpay
            // 
            this.tlpay.Location = new System.Drawing.Point(595, 247);
            this.tlpay.Name = "tlpay";
            this.tlpay.Size = new System.Drawing.Size(75, 23);
            this.tlpay.TabIndex = 8;
            this.tlpay.Text = "TL ÖDE";
            this.tlpay.UseVisualStyleBackColor = true;
            this.tlpay.Click += new System.EventHandler(this.tlpay_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(489, 85);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(48, 13);
            this.label2.TabIndex = 9;
            this.label2.Text = "Ödenen:";
            // 
            // lblPay
            // 
            this.lblPay.AutoSize = true;
            this.lblPay.Location = new System.Drawing.Point(544, 85);
            this.lblPay.Name = "lblPay";
            this.lblPay.Size = new System.Drawing.Size(0, 13);
            this.lblPay.TabIndex = 10;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(489, 118);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(45, 13);
            this.label3.TabIndex = 11;
            this.label3.Text = "Toplam:";
            // 
            // lblTotal
            // 
            this.lblTotal.AutoSize = true;
            this.lblTotal.Location = new System.Drawing.Point(541, 118);
            this.lblTotal.Name = "lblTotal";
            this.lblTotal.Size = new System.Drawing.Size(0, 13);
            this.lblTotal.TabIndex = 12;
            // 
            // txtPay
            // 
            this.txtPay.Location = new System.Drawing.Point(489, 249);
            this.txtPay.Name = "txtPay";
            this.txtPay.Size = new System.Drawing.Size(100, 20);
            this.txtPay.TabIndex = 13;
            // 
            // DeletePerson
            // 
            this.DeletePerson.Location = new System.Drawing.Point(587, 160);
            this.DeletePerson.Name = "DeletePerson";
            this.DeletePerson.Size = new System.Drawing.Size(75, 23);
            this.DeletePerson.TabIndex = 14;
            this.DeletePerson.Text = "Kişiyi Sil";
            this.DeletePerson.UseVisualStyleBackColor = true;
            this.DeletePerson.Click += new System.EventHandler(this.DeletePerson_Click);
            // 
            // showCredits
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(674, 333);
            this.Controls.Add(this.DeletePerson);
            this.Controls.Add(this.txtPay);
            this.Controls.Add(this.lblTotal);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.lblPay);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.tlpay);
            this.Controls.Add(this.value);
            this.Controls.Add(this.txt);
            this.Controls.Add(this.pay);
            this.Controls.Add(this.delete);
            this.Controls.Add(this.add);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.lblName);
            this.Controls.Add(this.dataGridView1);
            this.Name = "showCredits";
            this.Text = "showCredits";
            this.Load += new System.EventHandler(this.showCredits_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.Label lblName;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button add;
        private System.Windows.Forms.Button delete;
        private System.Windows.Forms.Button pay;
        private System.Windows.Forms.Label txt;
        private System.Windows.Forms.Label value;
        private System.Windows.Forms.Button tlpay;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label lblPay;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label lblTotal;
        private System.Windows.Forms.TextBox txtPay;
        private System.Windows.Forms.Button DeletePerson;
    }
}