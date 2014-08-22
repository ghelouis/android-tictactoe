package com.dev.guillaume.tictactoereboot.tabs;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dev.guillaume.tictactoereboot.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryFragment extends Fragment {
    ListView listView;
    ArrayList<String> list = new ArrayList<String>();

    public HistoryFragment() {
    }

    public void add_winner(int winner) {
        String date = SimpleDateFormat.getDateTimeInstance().format(new Date());
        if (winner == 0)
            list.add(date + "\n\tdraw");
        if (winner == 1)
            list.add(date + "\n\tplayer X won");
        if (winner == 2)
            list.add(date + "\n\tplayer O won");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        listView = (ListView) view.findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
