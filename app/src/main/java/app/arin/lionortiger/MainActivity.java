package app.arin.lionortiger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player {

        ONE, TWO , NoOne
    }

    Player currentPlayer = Player.ONE;

    Player[] playerChoices = new Player[9];

    int[][] winnerRowsColumns = {
            {0, 1 , 2},
            {3, 4, 5},
            {6, 7 , 8},
            {0 , 3 , 6} ,
            {1, 4, 7} ,
            {2, 5, 8} ,
            {0 , 4 , 8} ,
            {2 , 4 , 6}
    };

    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerChoices[0] = Player.NoOne;
        playerChoices[1] = Player.NoOne;
        playerChoices[2] = Player.NoOne;
        playerChoices[3] = Player.NoOne;
        playerChoices[4] = Player.NoOne;
        playerChoices[5] = Player.NoOne;
        playerChoices[6] = Player.NoOne;
        playerChoices[7] = Player.NoOne;
        playerChoices[8] = Player.NoOne;
    }

    public void imageViewIsTapped(View imageView) {
        ImageView tappedImageView = (ImageView) imageView;
        int tiTag = Integer.parseInt(tappedImageView.getTag().toString());

        if(playerChoices[tiTag] == Player.NoOne && gameOver == false) {
            tappedImageView.setTranslationX(-2000);


            playerChoices[tiTag] = currentPlayer;
            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {
                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;
            }

            tappedImageView.setBackground(null);
            tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);

            Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();

            for (int[] winnerColumns : winnerRowsColumns) {
                if (playerChoices[winnerColumns[0]] ==
                        playerChoices[winnerColumns[1]]
                        && playerChoices[winnerColumns[1]]
                        == playerChoices[winnerColumns[2]] && playerChoices[winnerColumns[0]] != Player.NoOne) {
                    gameOver = true;
                    String winnerOfGame = "";
                    if (currentPlayer == Player.ONE) {
                        winnerOfGame = "Player Two";
                    } else if (currentPlayer == Player.TWO) {
                        winnerOfGame = "Player One";
                    }
                    Toast.makeText(this, winnerOfGame + " is the Winner", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
