package fr.rolandl.sample.smartshareactionprovider;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import fr.rolandl.smartshareactionprovider.SmartShareActionProvider;
import fr.rolandl.smartshareactionprovider.SmartShareActionProvider.OnShareTargetSelectedListener;
import fr.rolandl.smartshareactionprovider.SmartShareActionProvider.ShareIcon;

/**
 * @author Ludovic Roland
 * @since 2015.01.02
 */
public final class MainActivity
    extends ActionBarActivity
{

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    //Step 1 : Create an instance of a SmartShareActionProvider
    final SmartShareActionProvider provider = new SmartShareActionProvider(this, ShareIcon.White);
    // final SmartShareActionProvider provider = new SmartShareActionProvider(this, getResources().getDrawable(R.drawable.ic_launcher));

    //Step 2 : Create an intent to share and set it to the SmartShareActionProvider
    final Intent intent = new Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, "Hi !");
    provider.setShareIntent(intent);

    //Step 3 : Create your menu item
    final MenuItem item = menu.add(Menu.NONE, Menu.NONE, Menu.NONE, "share");
    MenuItemCompat.setActionProvider(item, provider);
    MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);

    //Step 4 : Override the OnShareTargetSelectedListener listener
    provider.setOnShareTargetSelectedListener(new OnShareTargetSelectedListener()
    {

      @Override
      public boolean onShareTargetSelected(SmartShareActionProvider source, Intent intent)
      {
        //Step 5 : Retrieve the app choosed by the user
        final String app = intent.getComponent().getPackageName();

        //Step 6 : Change the intent content depending on the app
        if ("com.google.android.gm".equals(app) == true)
        {
          intent.setType("message/rfc822");
          intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
          intent.putExtra(Intent.EXTRA_TEXT, "gmail");
        }
        else if ("com.google.android.keep".equals(app) == true)
        {
          intent.putExtra(Intent.EXTRA_TEXT, "keep");
        }

        return false;
      }

    });

    return super.onCreateOptionsMenu(menu);
  }

}
