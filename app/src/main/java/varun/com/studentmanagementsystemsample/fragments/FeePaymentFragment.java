package varun.com.studentmanagementsystemsample.fragments;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.adapter.FeePaymentAdapter;
import varun.com.studentmanagementsystemsample.bean.FeePaymentBean;

/**
 * Created by Varun on 4/2/2016.
 */
public class FeePaymentFragment extends Fragment {

    RecyclerView feePaymentRV;
    ArrayList<FeePaymentBean> list;
    FeePaymentAdapter adapter;
    ProgressBar feeProgressBar;

    TableRow feePaymentTableRow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_fee_payment, container, false);

        feeProgressBar = (ProgressBar) rootView.findViewById(R.id.fee_progress_bar);
        feePaymentRV = (RecyclerView) rootView.findViewById(R.id.rvFeePayment);
        feePaymentTableRow = (TableRow) rootView.findViewById(R.id.fee_payment_rows);

        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(feeProgressBar, "progress", 0, 75);
        progressAnimator.setDuration(3000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();

        list = new ArrayList<>();
        list.add(new FeePaymentBean("School Tution Fee", "8333", "18 Jan 2016 15:33", "1500"));
        list.add(new FeePaymentBean("Bus Fee", "8333", "18 Jan 2016 15:33", "1500"));
        list.add(new FeePaymentBean("Library Fee", "8333", "18 Jan 2016 15:33", "1500"));
        list.add(new FeePaymentBean("Admission Fee", "8333", "18 Jan 2016 15:33", "1500"));
        list.add(new FeePaymentBean("Bus Fee", "8333", "18 Jan 2016 15:33", "1500"));
        list.add(new FeePaymentBean("Library Fee", "8333", "18 Jan 2016 15:33", "1500"));
        list.add(new FeePaymentBean("Admission Fee", "8333", "18 Jan 2016 15:33", "1500"));
        list.add(new FeePaymentBean("Bus Fee", "8333", "18 Jan 2016 15:33", "1500"));
        list.add(new FeePaymentBean("Library Fee", "8333", "18 Jan 2016 15:33", "1500"));
        list.add(new FeePaymentBean("Admission Fee", "8333", "18 Jan 2016 15:33", "1500"));

        adapter = new FeePaymentAdapter(FeePaymentFragment.this.getActivity(), list);
        feePaymentRV.setAdapter(adapter);
        feePaymentRV.setLayoutManager(new LinearLayoutManager(getActivity()));

//        addTableRow();

        return rootView;
    }

    public void addTableRow() {
        TextView componentContent = new TextView(FeePaymentFragment.this.getActivity());
        TextView amountPaidContent = new TextView(FeePaymentFragment.this.getActivity());
        TextView feesPaidContent = new TextView(FeePaymentFragment.this.getActivity());
        TextView feesDueContent = new TextView(FeePaymentFragment.this.getActivity());

        for (int i = 0; i < list.size(); i++) {

            componentContent.setText(list.get(i).getComponent());
            amountPaidContent.setText(list.get(i).getAmount());
            feesPaidContent.setText(list.get(i).getDate());
            feesDueContent.setText(list.get(i).getFeesDue());

            feePaymentTableRow.addView(componentContent);
            feePaymentTableRow.addView(amountPaidContent);
            feePaymentTableRow.addView(feesPaidContent);
            feePaymentTableRow.addView(feesDueContent);
        }
    }
}
