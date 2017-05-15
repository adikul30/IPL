package ipl.ipl;

import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    List<String> players;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");
        RecyclerView recylerView = (RecyclerView) findViewById(R.id.team_recycler_view);
        recylerView.setLayoutManager(new LinearLayoutManager(this));
        players=selectTeam(position);
        TeamAadapter adapter = new TeamAadapter(players);
        recylerView.setAdapter(adapter);

    }

    public List<String> selectTeam(int choice) {
        List<String> playerList = null;
        AppCompatActivity activity = DetailActivity.this;
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.getElevation();
        switch(choice) {
            //Enter different playerlist here

            case 0 :    String[] MUMBAI = {"Rohit Sharma (Captain)","Ambati Rayudu","Harbhajan Singh", "Hardik Pandya", "J Suchith", "Jasprit Bumrah"," Nitish Rana"," Parthiv Patel", "R Vinay Kumar", "Shreyas Gopal", "Siddhesh Lad", "Lasith Malinga", "Lendl Simmons", "Kieron Pollard", "Mitchell McClenaghan", "Tim Southee", "Jitesh Sharma", "Krunal Pandya", "Deepak Punia", "Jos Buttler", "Nicholas Pooran", "Mitchell Johnson", "Gowtham K", "Saurabh Tiwary"," Asela Gunarathne", "K Khejroliya"," Karn Sharma"};
                        playerList = Arrays.asList(MUMBAI);
                        actionBar.setTitle("Mumbai Indians");
                        break;

            case 1 :    String[] DELHI = {"Zaheer Khan (Captain)","Amit Mishra", "Jayant Yadav", "Mayank Agarwal","Mohammad Shami", "Shahbaz Nadeem", "Shreyas Iyer", "Sanju Samson","Rishabh Pant", "Chris Morris", "Carlos Brathwaite", "Sam Billings", "SK Ahmed", "Pratyush Singh", "Chama Milind", "Karun Nair", "Angelo Mathews", "Corey Anderson", "Kagiso Rabada", "Pat Cummins", "Ankeet Bawne", "Aditya Tare", "M Ashwin", "Navseep Saini", "Shashank Singh"};
                        playerList = Arrays.asList(DELHI);
                        actionBar.setTitle("Delhi Daredevils");
                        break;

            case 2 :    String[] GUJARAT = {"Suresh Raina (Captain)", "Ravindra Jadeja", "Dhawal Kulkarni", "Praveen Kumar","Pradeep Sangwan", "Shivil Kaushik", "Brendon McCullum", "Dwayne Bravo", "James Faulkner", "Dwayne Smith", "Aaron Finch"," Andrew Tye", "Shabad Jakati", "Jaydev Shah"," Nathu Singh"," Basil Thampy", "TS Baroka", "Manpreet Gony"," Jason Roy", "Munaf Patel", "Chirag Suri"," Shelley Shaurya"," Shubam Agarwal", "Pratham Singh", "Aksh Deep Nath" ,"Ishan Kishan"};
                        playerList = Arrays.asList(GUJARAT);
                        actionBar.setTitle("Gujarat Lions");
                        break;

            case 3 :    String[] PUNJAB = {"Glenn Maxwell (Captain)", "Manan Vohra", "Axar Patel"," Anureet Singh", "Gurkeerat Singh", "Nikhil Naik", "Sandeep Sharma", "Shardul Thakur", "Wriddhiman Saha"," KC Cariappa"," David Miller", "Shaun Marsh"," Armaan Jafeer", "Pardeep Sahu", "Swapnil Singh"," Hashim Amla", "Mohit Sharma", "Marcus Stoinis"," Eoin Morgan", "Rahul Tewatia", "T Natarajan"," Matt Henry", "Varun Aaron", "Martin Guptill", "Darren Sammy", "Rinku Singh"};
                        playerList = Arrays.asList(PUNJAB);
                        actionBar.setTitle("Kings XI Punjab");
                        break;

            case 4 :    String[] KOLKATA = {"Gautam Gambhir (Captain)", "Kuldeep Yadav", "Manish Pandey", "Piyush Chawla", "Robin Uthappa"," Sheldon Jackson", "Suryakumar Yadav", "Ankit Rajpoot"," Umesh Yadav", "Yusuf Pathan", "Colin de Grandhomme ", "Sunil Narine"," Chris Lynn", "Shakib Al Hasan", "Trend Boult", "Chris Woakes", "Rishi Dhawan", "Nathan Coulter-Nile", "Rovman Powell", "R Sanjay Yadav", "Ishank Jaggi", "Darren Bravo", "Sayan Ghosh"};
                        playerList = Arrays.asList(KOLKATA);
                        actionBar.setTitle("Kolkata Knight Riders");
                        break;

            case 5 :    String[] PUNE = {"Steve Smith (captain)", "MS Dhoni"," Ajinkya Rahane", "Ankit Sharma", "Ishwar Pandey", "du Plessis", "Mitchell Marsh", "Baba Aparajith"," Ashok Dinda"," Jaskaran Singh", "Adam Zampa", "Rajat Bhatia", "Deepak Chahar", "Ben Stokes", "Jaidev Unadkat", "Rahul Chahar", "Saurabh Kumar", "Daniel Christian", "Milind Tandon", "Rahul Tripathi", "Manoj Tiwary", "Lockie Ferguson", "Ankush Bains"};
                        playerList = Arrays.asList(PUNE);
                        actionBar.setTitle("Rising Pune Supergiants");
                        break;

            case 6 :    String[] BANGALORE = {"Virat Kohli (Captain)", "Harshal Patel"," Kedar Jadhav"," Mandeep Singh", "Sreenath Aravind", "Yuzvendra Chahal", "Stuart Binny", "Sachin Baby", "Iqbal Abdulla", "Shane Watson", "AB de Villiers", "Travis Head", "Adam Milne", "Chris Gayle", "Akshay Karnewakar", "Samuel Badree", "Pawan Negi", "Tymal Mills", "Aniket Choudhary", "Praveen Dubey", "Billy Stanlake", "KL Rahul", "Sarfaraz Khan"};
                        playerList = Arrays.asList(BANGALORE);
                        actionBar.setTitle("Royal Challengers Bangalore");
                        break;

            case 7 :    String[] HYDERABAD = {"David Warner (Captain)"," Shikhar Dhawan", "Bhuvneshwar Kumar"," Bipul Sharma", "Naman Ojha", "Parvez Rasool", "Ricky Bhui", "Siddharth Kaul", "Deepak Hooda", "Ashish Nehra", "Yuvraj Singh", "Barinder Sran"," Abhimanyu Mithun", "Moises Henriques", "Kane Williamson", "Mustafizur Rahman", "Ben Cutting", "Vijay Shankar", "Tanmay Agarwal", "Mohammad Nabi"," Eklavya Dwivedi"," Rashid Khan"," Pravin Tambe", "Chris Jordan", "Ben Laughlin", "Mohammed Siraj"};
                        playerList = Arrays.asList(HYDERABAD);
                        actionBar.setTitle("Sunrisers Hyderabad");
                        break;

        }
        return playerList;
    }
}
