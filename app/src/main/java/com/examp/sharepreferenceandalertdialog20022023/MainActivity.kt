package com.examp.sharepreferenceandalertdialog20022023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var checkBoxRemember: CheckBox
    private lateinit var btnLogin: Button
    private lateinit var tvOutput: TextView
    private lateinit var imgDelete: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        edtEmail = findViewById(R.id.edit_text_email)
        edtPassword = findViewById(R.id.edit_text_password)
        checkBoxRemember = findViewById(R.id.check_box_save)
        btnLogin = findViewById(R.id.button_login)
        tvOutput = findViewById(R.id.text_view_output)
        imgDelete = findViewById(R.id.image_view_delete)
    }
}
