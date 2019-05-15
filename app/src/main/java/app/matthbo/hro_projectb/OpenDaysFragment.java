package app.matthbo.hro_projectb;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.matthbo.hro_projectb.adapter.OpenDaysAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class OpenDaysFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    public OpenDaysFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_open_days, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView_openDays);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        String[] dataset = new String[]{
                "Business IT & Management",
                "Communicatie",
                "Communication and Multimedia Design",
                "Creative Media and Game Technologies",
                "Crossmediale Communicatie",
                "ICT Service Management",
                "Informatica",
                "Media Design and Communication",
                "Technische Informatica" };
        mAdapter = new OpenDaysAdapter(dataset);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

}
