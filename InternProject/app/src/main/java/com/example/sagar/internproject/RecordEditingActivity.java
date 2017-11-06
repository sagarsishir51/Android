
package com.example.sagar.internproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RecordEditingActivity extends AppCompatActivity implements View.OnClickListener,KeyEvent.Callback {
    Button saveEditedData;
    EditText editedname;
    userInfoDao userDao;
    userInfo infoToEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_editing);
        saveEditedData=(Button)findViewById(R.id.buttonSaveEditedData);
        saveEditedData.setOnClickListener(this);
        editedname=(EditText)findViewById(R.id.editingName);
        DaoSession daoSession=((App)getApplication()).getDaoSession();
        userDao=daoSession.getUserInfoDao();
        try {


            Long id = getIntent().getLongExtra("id", 0);
            //Toast.makeText(getApplicationContext(),"in intend"+id,Toast.LENGTH_SHORT).show();

            infoToEdit = userDao.load(id);
            editedname.setText(infoToEdit.getName());
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),"in intend"+e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonSaveEditedData:{
                infoToEdit.setName(editedname.getText().toString().trim());
                userDao.update(infoToEdit);
                Toast.makeText(getApplicationContext(),"Successfully edited",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                setResult(1,intent);
                finish();
                //userInfo info=new userInfo();
                //info.setName(editedname.getText().toString().trim());

            }
        }

    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK: {
                finish();
                break;
            }
        }
        return true;
    }
}
