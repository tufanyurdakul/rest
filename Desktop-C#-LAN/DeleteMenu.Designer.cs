namespace LocalRest
{
    partial class DeleteMenu
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
            this.menuList = new System.Windows.Forms.ComboBox();
            this.btnOk = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // menuList
            // 
            this.menuList.FormattingEnabled = true;
            this.menuList.Location = new System.Drawing.Point(12, 29);
            this.menuList.Name = "menuList";
            this.menuList.Size = new System.Drawing.Size(121, 21);
            this.menuList.TabIndex = 0;
            // 
            // btnOk
            // 
            this.btnOk.Location = new System.Drawing.Point(162, 27);
            this.btnOk.Name = "btnOk";
            this.btnOk.Size = new System.Drawing.Size(75, 23);
            this.btnOk.TabIndex = 1;
            this.btnOk.Text = "OK";
            this.btnOk.UseVisualStyleBackColor = true;
            this.btnOk.Click += new System.EventHandler(this.btnOk_Click);
            // 
            // DeleteMenu
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(282, 86);
            this.Controls.Add(this.btnOk);
            this.Controls.Add(this.menuList);
            this.Name = "DeleteMenu";
            this.Text = "DeleteMenu";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ComboBox menuList;
        private System.Windows.Forms.Button btnOk;
    }
}