package eu.pl.snk.senseibunny.museomaster.controllers.NormalWorkerControllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import eu.pl.snk.senseibunny.museomaster.R
import eu.pl.snk.senseibunny.museomaster.controllers.AdminControllers.AddRoomFragment
import eu.pl.snk.senseibunny.museomaster.controllers.AdminControllers.AddUserFragment
import eu.pl.snk.senseibunny.museomaster.controllers.AdminControllers.ReportFragment
import eu.pl.snk.senseibunny.museomaster.controllers.AdminControllers.UserListFragment
import eu.pl.snk.senseibunny.museomaster.controllers.utilsControllers.BugFragment
import eu.pl.snk.senseibunny.museomaster.controllers.utilsControllers.EndedTaskListFragment
import eu.pl.snk.senseibunny.museomaster.controllers.utilsControllers.TaskListFragment
import eu.pl.snk.senseibunny.museomaster.databinding.ActivityAdminBinding
import eu.pl.snk.senseibunny.museomaster.databinding.ActivityNormalWorkerBinding
import eu.pl.snk.senseibunny.museomaster.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.sql.ResultSet

class NormalWorkerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private  lateinit var fragmentManage: FragmentManager
    private lateinit var binding: ActivityNormalWorkerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNormalWorkerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val toogle = ActionBarDrawerToggle(this,binding.drawerLayout, binding.toolbar,R.string.nav_open,R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        fragmentManage = supportFragmentManager
        openFragment(TaskListFragment())
        binding.navigationDrawer.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.taskList -> {openFragment(TaskListFragment())
            }

            R.id.taskFinished-> {openFragment(EndedTaskListFragment())}
            R.id.bug->{openFragment(BugFragment())}
            R.id.logout->finish()
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        else{
            super.getOnBackPressedDispatcher().onBackPressed()
        }
    }

    private fun openFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = fragmentManage.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

}