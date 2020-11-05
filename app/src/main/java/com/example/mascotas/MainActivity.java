package com.example.mascotas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mascotas.adapter.PageAdapter;
import com.example.mascotas.fragments.PerfilFragment;
import com.example.mascotas.fragments.RecyclerViewFragment;
import com.example.mascotas.menu.ActivityAcercade;
import com.example.mascotas.menu.ActivityContacto;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.actionbar);
        tabLayout =  findViewById(R.id.tabLayout);
        viewPager =  findViewById(R.id.viewPager);
        setUpViewPager();

        if (toolbar != null){
            setSupportActionBar(toolbar);
        }
    }

    private void setUpViewPager(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.casaperro);
        tabLayout.getTabAt(1).setIcon(R.drawable.perro);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btFav:
                Intent intent = new Intent(this, MascotasFavoritas.class);
                startActivity(intent);
                break;
            case R.id.Contacto:
                Intent intent1 = new Intent(this, ActivityContacto.class);
                startActivity(intent1);
            break;
            case R.id.Acercade:
                Intent intent2 = new Intent(this, ActivityAcercade.class);
                startActivity(intent2);
            break;
        }
        return super.onOptionsItemSelected(item);
    }

}