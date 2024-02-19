package eu.pl.snk.senseibunny.museomaster.controllers.PermissionTechWorker

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
import eu.pl.snk.senseibunny.museomaster.controllers.utilsControllers.*
import eu.pl.snk.senseibunny.museomaster.databinding.ActivityPermissionTechnicalWorkerBinding

class PermissionTechnicalWorkerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var fragmentManage: FragmentManager
    private lateinit var binding: ActivityPermissionTechnicalWorkerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionTechnicalWorkerBinding.inflate(layoutInflater)
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
            R.id.taskList -> {openFragment(TaskListFragment())}
            R.id.taskFinished -> {openFragment(EndedTaskListFragment())}
            R.id.taskAssignedTo -> {openFragment(AssignedToListFragment())}
            R.id.AssignTask -> {openFragment(UserSearchFragment())}
            R.id.ExhibitsList -> {openFragment(ExhibitsAssignedFragment())}
            R.id.bug -> {openFragment(BugFragment())}
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