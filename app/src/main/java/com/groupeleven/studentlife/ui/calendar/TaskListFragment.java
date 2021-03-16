import androidx.lifecycle.ViewModelProvider;

import com.groupeleven.studentlife.R;

public class TaskListFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, /*@Nullable*/ ViewGroup container, /*@Nullable*/ Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_a, container, false);
        editText = v.findViewById(R.id.edit_text);
        buttonOk = v.findViewById(R.id.button_ok);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence input = editText.getText();
                listener.onInputASent(input);
            }
        });
        return v;
    }



}