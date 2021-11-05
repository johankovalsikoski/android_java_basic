package johan.kovalsikoski.primo;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    FrameLayout frame1, frame2;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setupToolbar();
        setupDrawer();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) { // default android back arrow id
            drawer.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    private void findViews() {
        frame1 = findViewById(R.id.frame_1);
        frame2 = findViewById(R.id.frame_2);
        drawer = findViewById(R.id.drawer_layout);
    }

    private void setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // enable back arrow
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu); // replace back arrow

        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("This is a toolbar");
    }

    private void setupDrawer() {
        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return selectDrawerItem(item);
            }
        });
    }

    private boolean selectDrawerItem(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_top_amarelo:
                inflateFragmentAmarelo(R.id.frame_1);
                return true;

            case R.id.menu_bot_amarelo:
                inflateFragmentAmarelo(R.id.frame_2);
                return true;

            case R.id.menu_top_azul:
                inflateFragmentAzul(R.id.frame_1);
                return true;

            case R.id.menu_bot_azul:
                inflateFragmentAzul(R.id.frame_2);
                return true;

            case R.id.menu_top_vermelho:
                inflateFragmentVermelho(R.id.frame_1);
                return true;

            case R.id.menu_bot_vermelho:
                inflateFragmentVermelho(R.id.frame_2);
                return true;

            case R.id.menu_dice_toast:
                Toast.makeText(this, "" + (int) ((Math.random() * 6) + 1), Toast.LENGTH_SHORT).show();
                return true;

            default:
                return false;
        }


    }

    private void inflateFragmentAmarelo(int containerId) {
        getSupportFragmentManager().beginTransaction().replace(
                containerId, new FragmentAmarelo()
        ).commit();

        drawer.closeDrawers();
    }

    private void inflateFragmentAzul(int containerId) {
        getSupportFragmentManager().beginTransaction().replace(
                containerId, new FragmentAzul()
        ).commit();

        drawer.closeDrawers();
    }

    private void inflateFragmentVermelho(int containerId) {
        getSupportFragmentManager().beginTransaction().replace(
                containerId, new FragmentVermelho()
        ).commit();

        drawer.closeDrawers();
    }

}