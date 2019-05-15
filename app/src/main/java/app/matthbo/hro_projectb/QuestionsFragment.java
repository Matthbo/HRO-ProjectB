package app.matthbo.hro_projectb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class QuestionsFragment extends Fragment implements FragmentOnClickable{
    private View fragmentView;

    public QuestionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_questions, container, false);

        return fragmentView;
    }

    @Override
    public void fragmentOnClick(View v) {
        if (v.getId() == R.id.button_questions_send) {
            sendEmail();
        }
    }

    private void sendEmail(){
        String[] addresses = new String[] {"noreply@hr.nl"};
        String shareSub = ((EditText)fragmentView.findViewById(R.id.editText_questions_subject)).getText().toString();
        String shareBody = ((EditText)fragmentView.findViewById(R.id.editText_questions_body)).getText().toString();

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, addresses);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
        emailIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
        if (emailIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }
}
