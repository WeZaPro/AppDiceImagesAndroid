package com.taweesak.appdiceimagesandroid;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    Button button;
    ImageView diceImage1, diceImage2;
    TextView textView;
    AndroidViewModel androidViewModel;
    ModelDice modelNumber;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        androidViewModel = ViewModelProviders.of(this).get(AndroidViewModel.class);

        findView(view);
        // getValue from ViewModel

        androidViewModel.getData().observe(getActivity(), new Observer<ModelDice>() {
            @Override
            public void onChanged(ModelDice modelDice) {

                dicePreview(modelDice);
            }
        });

        // setValue to ViewModel
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice2Dice();

            }
        });

        return view;
    }

    private void dicePreview(ModelDice modelDice) {
        int[] dices1 = {
                R.drawable.dice_1,
                R.drawable.dice_2,
                R.drawable.dice_3,
                R.drawable.dice_4,
                R.drawable.dice_5,
                R.drawable.dice_6
        };
        int[] dices2 = {
                R.drawable.dice_1,
                R.drawable.dice_2,
                R.drawable.dice_3,
                R.drawable.dice_4,
                R.drawable.dice_5,
                R.drawable.dice_6
        };

        diceImage1.setImageResource(dices1[modelDice.diceA]);
        diceImage2.setImageResource(dices2[modelDice.diceB]);

        int diceTotal = (modelDice.diceA + 1) + (modelDice.diceB + 1);
        textView.setText("dic1 : " + (modelDice.diceA + 1) + " | dic2 : " + (modelDice.diceB + 1) + " | Total = " + diceTotal);
        //Toast.makeText(getActivity(), "Text : " + modelDice.dice1, Toast.LENGTH_LONG).show();

    }

    private void rollDice2Dice() {

        Random r1 = new Random();
        Random r2 = new Random();

        int randomNumber1 = r1.nextInt(6) + 0;
        int randomNumber2 = r2.nextInt(6) + 0;

        modelNumber = new ModelDice(randomNumber1, randomNumber2);
        androidViewModel.setNumber(modelNumber);

    }

    private void findView(View view) {
        button = view.findViewById(R.id.roll_button);
        textView = view.findViewById(R.id.textView);
        diceImage1 = view.findViewById(R.id.dice_image1);
        diceImage2 = view.findViewById(R.id.dice_image2);
    }
}
