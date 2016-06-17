package com.daavm.nextbit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class signupPage extends AppCompatActivity
                    implements NavigationView.OnNavigationItemSelectedListener{
    public void terms(View view)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Nextbit Terms of Service");
        alertDialog.setMessage("\n" + "Nextbit Terms of Service\n" +
                "\n" +
                " \n" +
                "\n" +
                "February 18. 2016\n" +
                "\n" +
                " \n" +
                "\n" +
                "Thanks for using our products and services (\"Services\"). The Services are provided by Nextbit Systems Inc. (\"Nextbit\"), located at 290 King Street Suite 9, San Francisco, CA 94107, United States. By using our Services, you are agreeing to these terms.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Using our Services\n" +
                "\n" +
                "Your use of the Nextbit Community (Forums) is governed by these Nextbit Community Terms of Service, together with the Nextbit Privacy Policy and Nextbit Terms of Service (collectively, the \"Terms\"). The Terms describe the permitted and prohibited uses of Nextbit Community, among other things. Your access or use of Nextbit Community evidences your acceptance of the then-current version of the Terms, and any related rules and guidelines posted on Nextbit branded web sites.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Nextbit reserves the right to modify the Terms at any time, effective upon posting of the modified Terms on Nextbit Community. Nextbit also reserves the right to apply, waive, or modify these Terms as they apply to a specific posting and user without affecting the application of these Terms to all other postings and users.\n" +
                "\n" +
                " \n" +
                "\n" +
                "\"Posting\" means, but is not limited to, any text, links, images, communications, opinions, software, data, and any other content any person provides on Nextbit Community, including, but not limited to message boards, chats, blogs, or otherwise. Your use of Nextbit Community, or any materials or services accessible through it, after a Posting or notification regarding modifications to the Terms constitutes your acceptance of those modifications. Your violation of any of the Terms may result in the suspension or termination of your access or use of Nextbit Community.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Participating in the Nextbit Community\n" +
                "\n" +
                "The Nextbit Community is intended to provide consumers with the opportunity to exchange useful and helpful information. We may edit or remove any posting we consider, in our sole discretion, to violate the Terms or be inappropriate for the Nextbit Community for any reason.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Appropriate Conduct\n" +
                "\n" +
                "Users of the Nextbit Community are responsible for exercising careful and appropriate judgment in evaluating and taking action based on other participants' postings on Nextbit Community, since such postings may reflect significantly different levels of knowledge and experience by participants. Users of the Nextbit Community agree that Nextbit is not responsible for the accuracy of the content of the Nextbit Community and will not be liable for any damages incurred as a result of their use of any such content. Participants may post hypertext links to content hosted and maintained by third parties. Nextbit has no obligation to monitor these linked sites, and is not responsible for them. Accessing any such linked sites is done entirely at the user's own risk.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Posting to the Nextbit Community\n" +
                "\n" +
                "Users of the Nextbit Community agree not to upload, post, or otherwise transmit any content (including but not limited to text, links, communications, software, images, sounds, data, or other information) that includes any of the following inappropriate content:\n" +
                "\n" +
                "Any personal information belonging either to the poster or another person, such as full name, address, phone number, personal email address, serial numbers and support ticket repair numbers;\n" +
                "Spam such as advertisements for other web sites and services, chain letters, or pyramid schemes, unauthorized solicitation (as approved by Nextbit);\n" +
                "Excessive posting or padding posts;\n" +
                "Contentl that is libelous, fraudulent, unlawful, defamatory, pornographic, obscene, profane, abusive, offensive, threatening, hateful, or otherwise objectionable;\n" +
                "Discussion of illegal activities or providing links to other websites containing such information;\n" +
                "Discussions that veer off topic, are unrelated to resolving the issue at hand, or abuse any company or product;\n" +
                "Discussions of moderator actions on the boards. If you need to comment on a moderator action, please private message any administrator/moderator;\n" +
                "Material, the posting of which violates any party's copyright or other intellectual property rights;\n" +
                "Posting, publishing, uploading, reproducing, transmitting or distributing in any way any content belonging to Nextbit, or derivative works with respect thereto;\n" +
                "Posting or transmitting any information or software containing a virus, worm, Trojan horse, or other damaging or destructive component;\n" +
                "Posting a link directing users to any information or content that, if posted on the Nextbit Community, would constitute a violation of the Terms or of any State or Federal law;\n" +
                "\"Bombing\" the Nextbit Community or individual threads with repetitive or meaningless posts, unrelated to the purpose of the Nextbit Community, excessive cross-posting; \"bumping\" or making posts with no new useful content in order to move them to the top of the subject area.\n" +
                "Attacks in such a way as to incite or perpetuate an argument or conflict; creating usernames to attack other users' identities; impersonating other individuals or negatively representing one's identity or qualifications; posts made under secondary user names or other aliases for the purpose of either endorsing or denigrating others; posts that breach any participant's privacy by including name, address, phone, email address, or any other identifying information;\n" +
                "Posts that describe how to violate Nextbit policies or terms or conditions are considered unacceptable and will have the offensive language deleted;\n" +
                "Posts that discuss hacking and/or rooting a wireless phone from any carrier will be removed;\n" +
                "Posts that discuss how to circumvent Nextbit's security;\n" +
                "Posts that discuss any litigation or potential litigation (past, present, future or otherwise) is prohibited;\n" +
                "Kudos abuse including soliciting others for kudos, or giving an excessive number of kudos to a specific user;\n" +
                "User may not repost any private communications they have had with Nextbit representatives on the boards.\n" +
                "Please Note: Nextbit reserves the right to remove or move any posts or any profile content either deems inappropriate.\n" +
                "\n" +
                "  \n" +
                "\n" +
                "Registration Requirements\n" +
                "\n" +
                "While registration is not required to read postings on the Nextbit Community, users must register an account with Nextbit in order to post to the Nextbit Community. Nextbit may refuse to grant you, and you may not use, a username that is already being used by someone else, belongs to or impersonates another person, violates the intellectual property or other rights of any person (including but not limited to trademark rights), is offensive, or that Nextbit rejects for any other reason in its sole discretion. Participants agree that all information provided in a profile is accurate, up to date, and complete, and that it will be kept updated. Nextbit may terminate your account if any of the information provided is found to be inaccurate, out of date, or incomplete.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Privacy Concerns\n" +
                "\n" +
                "Nextbit strongly discourages users from posting personally identifiable information in the Nextbit Community. Any information posted on the Nextbit Community is at the user's own risk. Nextbit respects and protects the privacy of our customers and those who use our Web sites, and the Nextbit Privacy Policy provides details of our approach to privacy and how we collect, use and protect personal information. Please note information posted on the Nextbit Community is not protected and can be easily obtained and used by others.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Moderators\n" +
                "\n" +
                "Nextbit reserves the right to manage the postings on the Nextbit Community to provide an orderly presentation of this information. To effectively manage the Nextbit Community, Nextbit may designate employees or others to act as moderators and administrators for the Nextbit Community (\"Moderators\"). These Moderators are the only representatives of Nextbit authorized to manage the Nextbit Community. Any Nextbit employees who are not designated as Moderators or Employees are not authorized to represent themselves on the Nextbit Community as Nextbit employees. Authorized Nextbit Employees are distinguished by the Rank of \"Employee\" and/or an official Nextbit logo as an Avatar.  Nextbit is not responsible for content provided by any Nextbit employee who is not designated as a Moderator or an Employee.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Community Monitoring\n" +
                "\n" +
                "Users are asked to help Nextbit keep the Nextbit Community a valuable and enjoyable information resource for all participants by notifying us of any offending messages or other violations of these Terms. To advise us of such a posting, click the confidential \"Report Inappropriate Content\" link on the applicable post. Repeat offenders will be contacted by email and eventually banned from the Nextbit Community. If violations are egregious in nature, we will contact the appropriate authorities.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Modification and discontinuance of the Nextbit Community\n" +
                "\n" +
                "Nextbit reserves the right at any time to delete, modify, suspend, or discontinue, temporarily or permanently, the Nextbit Community (or any part of the Nextbit Community, including any postings) with or without notice. Users agree that Nextbit will not be liable to users or third parties for any modification, suspension, or discontinuance of the Nextbit Community.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Termination\n" +
                "\n" +
                "A user's privilege to utilize or access the Nextbit Community may be terminated by Nextbit immediately and without notice if the user fails to comply with any term or condition of the Terms. Upon such termination, the user must immediately cease accessing or utilizing the Nextbit Community and agree not to re-register or otherwise make use of the Nextbit Community. Furthermore, the user acknowledges that Nextbit reserves the right to take action technical, legal, or otherwise to block, nullify, or deny the user's ability to access the Nextbit Community. The user understands that Nextbit may exercise this right in its sole discretion.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Disclaimer of Warranties and Limitation of Liability\n" +
                "\n" +
                "MOST OF THE CONTENT POSTED TO THE NEXTBIT COMMUNITY IS PROVIDED BY THIRD PARTIES NOT AFFILIATED WITH NEXTBIT. THIRD-PARTY CONTENT IS THE SOLE RESPONSIBILITY OF THE PERSON ORIGINATING THAT CONTENT. THE USER AGREES THAT NEXTBIT DOES NOT CONTROL, AND IS NOT RESPONSIBLE IN ANY WAY FOR, THIS THIRD-PARTY CONTENT. ADDITIONALLY, THE USER AGREES THAT NEXTBIT IS NOT LIABLE FOR, AND THE USER SHALL INDEMNIFY AND HOLD NEXTBIT, AND ITS SUBSIDIARIES, AFFILIATES, OFFICERS, AGENTS, AND EMPLOYEES HARMLESS FROM ANY CLAIM, INCLUDING REASONABLE ATTORNEYS' FEES, MADE BY ANY THIRD PARTY RELATING TO OR ARISING OUT OF CONTENT SUBMITTED, POSTED, TRANSMITTED, OR MADE AVAILABLE THROUGH NEXTBIT COMMUNITY, USE OF NEXTBIT COMMUNITY, VIOLATION OF THE TERMS OR VIOLATION OF ANY RIGHTS OF ANOTHER. THE CONTENT ON NEXTBIT COMMUNITY IS \"AS IS\" AND CARRIES NO WARRANTIES. NEXTBIT DOES NOT WARRANT OR GUARANTEE THE ACCURACY, RELIABILITY, COMPLETENESS, USEFULNESS, NON-INFRINGEMENT OF INTELLECTUAL PROPERTY RIGHTS, OR QUALITY OF ANY CONTENT ON NEXTBIT COMMUNITY, REGARDLESS OF WHO ORIGINATES THAT CONTENT. NEXTBIT DOES NOT WARRANT THAT NEXTBIT COMMUNITY IS SECURE, FREE FROM BUGS, VIRUSES, INTERRUPTION, ERRORS, OR OTHER LIMITATIONS. YOU EXPRESSLY UNDERSTAND AND AGREE THAT YOU BEAR ALL RISKS ASSOCIATED WITH USING OR RELYING ON THAT CONTENT. NEXTBIT IS NOT LIABLE OR RESPONSIBLE IN ANY WAY FOR ANY CONTENT POSTED ON OR LINKED FROM NEXTBIT COMMUNITY, INCLUDING, BUT NOT LIMITED TO, ANY ERRORS OR OMISSIONS IN CONTENT, OR FOR ANY LOSSES OR DAMAGE OF ANY KIND INCURRED AS A RESULT OF THE USE OF OR RELIANCE ON ANY CONTENT. TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW, NEXTBIT AND ITS REPRESENTATIVES ARE NOT LIABLE FOR ANY INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES (INCLUDING DAMAGES RELATING TO LOSS OF BUSINESS, TELECOMMUNICATION FAILURES, LOSS, CORRUPTION, SECURITY OR THEFT OF DATA, LOSS OF PROFITS OR INVESTMENT, OR THE LIKE), WHETHER BASED ON BREACH OF CONTRACT, BREACH OF WARRANTY, TORT (INCLUDING NEGLIGENCE), STRICT LIABILITY, PRODUCT LIABILITY, OR OTHERWISE, EVEN IF Nextbit OR ITS REPRESENTATIVES HAVE BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES AND EVEN IF A REMEDY IS FOUND TO HAVE FAILED OF ITS ESSENTIAL PURPOSE. THE LIMITATIONS OF DAMAGES SET FORTH ABOVE ARE FUNDAMENTAL ELEMENTS OF THE BASIS OF THE AGREEMENT BETWEEN NEXTBIT AND THE USER.\n" +
                "\n" +
                " \n" +
                "\n" +
                "About these Terms\n" +
                "\n" +
                "We may modify these terms or any additional terms that apply to a Service to, for example, reflect changes to the law or changes to our Services. You should look at the terms regularly. We'll post notice of modifications to these terms on this page. We'll post notice of modified additional terms in the applicable Service. Changes will not apply retroactively and will become effective no sooner than fourteen days after they are posted. However, changes addressing new functions for a Service or changes made for legal reasons will be effective immediately. If you do not agree to the modified terms for a Service, you should discontinue your use of that Service. If there is a conflict between these terms and the additional terms, the additional terms will control for that conflict. These terms control the relationship between Nextbit and you. They do not create any third party beneficiary rights. If you do not comply with these terms, and we don't take action right away, this doesn't mean that we are giving up any rights that we may have (such as taking action in the future). If it turns out that a particular term is not enforceable, this will not affect any other terms. The laws of California, U.S.A., excluding California's conflict of laws rules, will apply to any disputes arising out of or relating to these terms or the Services. All claims arising out of or relating to these terms or the Services will be litigated exclusively in the federal or state courts of San Francisco County, California, USA, and you and Nextbit consent to personal jurisdiction in those courts.\n" +
                "\n");
        alertDialog.setButton("I got it, thanks!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.setIcon(R.drawable.ic_format_list_bulleted_white_24dp);
        alertDialog.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ////////////////
        //Choose Theme//
        ////////////////
        final Activity activity = this;
        final Boolean loged = preferences.getBoolean("loged", false);
        boolean midnightTheme = preferences.getBoolean("midnightTheme", false);
        boolean mintTheme = preferences.getBoolean("mintTheme", false);
        boolean electricTheme = preferences.getBoolean("electricTheme", false);

        if (midnightTheme) {
            setTheme(R.style.Midnight);
        } else if (mintTheme) {
            setTheme(R.style.Mint);
        } else if (electricTheme) {
            setTheme(R.style.Electric);
        } else {
            setTheme(R.style.Electric);
        }
        super.onCreate(savedInstanceState);
        //Mover layou arriba cuando el teclado esta abierto
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main_signup);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setVerticalScrollBarEnabled(false);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.NavigationView);
        navigationView.setScrollBarSize(0);
        navigationView.setNavigationItemSelectedListener(this);

        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                CheckBox check = (CheckBox)findViewById(R.id.check);
                EditText name = (EditText)findViewById(R.id.name);
                EditText lastname = (EditText)findViewById(R.id.lastname);
                EditText Username = (EditText)findViewById(R.id.Username);
                EditText Password = (EditText)findViewById(R.id.Password);
                EditText Password2 = (EditText)findViewById(R.id.Password2);
                EditText Email = (EditText)findViewById(R.id.Email);
                EditText Email2 = (EditText)findViewById(R.id.Email2);
                String username = Username.getText().toString();
                String lastnameF = lastname.getText().toString();
                String nameF = name.getText().toString();
                String password = Password.getText().toString();
                String password2 = Password2.getText().toString();
                String email = Email.getText().toString();
                String email2 = Email2.getText().toString();
                if (check.isChecked()){
                    if(email.equals(email2)){
                        if(password.equals(password2)){
                            Intent logedBefore = new Intent(getApplicationContext(), signup.class);
                            logedBefore.putExtra("user", username);
                            logedBefore.putExtra("lastname", lastnameF);
                            logedBefore.putExtra("name", nameF);
                            logedBefore.putExtra("pass", password);
                            logedBefore.putExtra("email", email);
                            editor.remove("loged" + Boolean.valueOf(loged));
                            editor.apply();
                            editor.putBoolean("loged", true);
                            editor.commit();
                            startActivity(logedBefore);
                        } else {
                            Toast.makeText(getApplicationContext(), "The introduced password doesn't match! Please retype it", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        if(password.equals(password2)) {
                            Toast.makeText(getApplicationContext(), "The introduced email doesn't match! Please retype it", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "The introduced email and password don't match! Please retype it", Toast.LENGTH_LONG).show();
                        }
                        }
                } else{
                    Toast.makeText(getApplicationContext(), "You haven't accepted Terms of Service!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.about) {
            Intent intent2 = new Intent(this,about.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }
        else if (id == R.id.donate) {
            Intent intent2 = new Intent(this,donationsScreen.class);
            startActivity(intent2);
        }
        else if (id == R.id.india) {
            Intent intent2 = new Intent(this,India.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }
        else if (id == R.id.store) {
            Intent intent2 = new Intent(this,store.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        } else if (id == R.id.nav_wiki) {
            Intent intent2 = new Intent(this,wiki.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        } else if (id == R.id.nav_discover) {
            Intent intent2 = new Intent(this,Discover.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        } else if (id == R.id.nav_themes) {
            Intent intent2 = new Intent(this,Preferences.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        } else if (id == R.id.Community) {
            Intent intent2 = new Intent(this,MainActivity.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }  else if (id == R.id.messages) {
            Intent intent2 = new Intent(this,Messages.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }  else if (id == R.id.notifications) {
            Intent intent2 = new Intent(this,Notifications.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }  else if (id == R.id.signout) {
            Intent intent2 = new Intent(this,Login.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }   else if (id == R.id.forumsettings) {
            Intent intent2 = new Intent(this,forumsettings.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
