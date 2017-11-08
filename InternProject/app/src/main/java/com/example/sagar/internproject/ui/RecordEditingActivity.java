
package com.example.sagar.internproject.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sagar.internproject.App;
import com.example.sagar.internproject.DaoSession;
import com.example.sagar.internproject.R;
import com.example.sagar.internproject.model.userInfo;
import com.example.sagar.internproject.userInfoDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordEditingActivity extends AppCompatActivity implements KeyEvent.Callback {
    @BindView(R.id.buttonSaveEditedData)
    Button saveEditedData;
    @BindView(R.id.editingName)
    EditText editedname;
    userInfoDao userDao;
    userInfo infoToEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_editing);
        DaoSession daoSession=((App)getApplication()).getDaoSession();
        userDao=daoSession.getUserInfoDao();
        ButterKnife.bind(this);
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
    @OnClick(R.id.buttonSaveEditedData)
    void addUserRecord() {
        infoToEdit.setName(editedname.getText().toString().trim());
        userDao.update(infoToEdit);
        Toast.makeText(getApplicationContext(),"Successfully edited",Toast.LENGTH_SHORT).show();
        finish();
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
