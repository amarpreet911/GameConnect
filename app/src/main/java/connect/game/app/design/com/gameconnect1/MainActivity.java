package connect.game.app.design.com.gameconnect1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
//tage represents the position
    ImageView counter;
    String selectedPlayer = "red";
    int tappedCounter = 0;
    boolean gameIsActive = true;
    String whoWon = null;
    String game = "NotDecided";
boolean gameIsOver;
    //unPlay means unplayed
    String [] gameState = {"unPlay", "unPlay", "unPlay", "unPlay", "unPlay", "unPlay", "unPlay", "unPlay"
                            , "unPlay" };
    int [][] winningPosition = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {
    //gets the image you have clicked upon
    counter = (ImageView) view;
    //sets without displaying
    System.out.println(counter.getTag().toString());
    tappedCounter = Integer.parseInt(counter.getTag().toString());

    if (gameState[tappedCounter].equals("unPlay") && gameIsActive) {
          gameState[tappedCounter] = selectedPlayer; //sets it to red or green

        if (selectedPlayer.equals("red")) {
            counter.setImageResource(R.drawable.red_btn);
            selectedPlayer = "green";
            System.out.println("red was entered, now selectedplayer is " + selectedPlayer.toUpperCase());
        } else if (selectedPlayer.equals("green")) {
            counter.setImageResource(R.drawable.green_btn);
            selectedPlayer = "red";
            System.out.println("green was entered, now selectedplayer is " + selectedPlayer.toUpperCase());
        }

        //shows the animation behind moving the object on the screen
        counter.setTranslationY(-1000f);
        counter.animate().translationYBy(1000f).setDuration(300);
        counter.animate().rotation(36000f);//.setDuration(2000);

        for( int winner[] : winningPosition){
            if(gameState[winner[0]].equals(gameState[winner[1]]) &&
                    gameState[winner[1]].equals(gameState[winner[2]]) &&
                    (gameState[winner[1]] != "unPlay")){
                gameIsActive = false;
                System.out.println("the winner is " + gameState[winner[0]].toUpperCase());
                whoWon = gameState[winner[0]].toUpperCase();
                game = "won";
            }
            //if nobody won
            else{  gameIsOver = true;

                for(String winOrNot : gameState){
                if(winOrNot.equals("unPlay"))
                    gameIsOver = false; //if even one is unused then no game over
                }
        if(gameIsOver){
            gameIsOver = true;
            gameIsActive = false;
            game = "draw";
            }
         }
        }endMessage();
        game = "NotDecided";
    }
   }

   public void endMessage(){
       if(game.equals("won")) {
           TextView txtWinnerText = (TextView) findViewById(R.id.plyAgnText);
           txtWinnerText.setText(whoWon+ " Won");
           LinearLayout lnrWinLayout = (LinearLayout) findViewById(R.id.plyAgainLayout);
           lnrWinLayout.setVisibility(View.VISIBLE);

       }
       else if (game.equals("draw")) {
           TextView txtWinnerText = (TextView) findViewById(R.id.plyAgnText);
           txtWinnerText.setText("Its a Draw");
           LinearLayout lnrWinLayout = (LinearLayout) findViewById(R.id.plyAgainLayout);
           lnrWinLayout.setVisibility(View.VISIBLE);
         //  game = "NotDecided";
       }
   }
    public void plyAgnClick(View view){

        gameIsActive = true;
        LinearLayout lnrWinLayout = (LinearLayout) findViewById(R.id.plyAgainLayout);
        lnrWinLayout.setVisibility(View.INVISIBLE);

        selectedPlayer = "red";
        for(int i = 0; i < gameState.length; i++ ){
            gameState[i] = "unPlay";
        }
        GridLayout mainGridLayout = (GridLayout)findViewById(R.id.mainGridLayout);

        //numb of child in the layout mainGridLayout.getChildCount()
        for(int i = 0; i < mainGridLayout.getChildCount(); i++){
            //setImageResource(0);   sets the image to an empty image
            ((ImageView)mainGridLayout.getChildAt(i)).setImageResource(0);
            //mainGridLayout.getChildCount()
        }
    }
}
