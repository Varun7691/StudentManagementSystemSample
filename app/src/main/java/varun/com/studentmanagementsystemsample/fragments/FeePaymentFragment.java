package varun.com.studentmanagementsystemsample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_fee_payment, container, false);

        feePaymentRV = (RecyclerView) rootView.findViewById(R.id.rvFeePayment);

        list = new ArrayList<>();
        list.add(new FeePaymentBean("School Tution Fee", "8333", "18 Jan 2016 15:33"));
        list.add(new FeePaymentBean("Bus Fee", "8333", "18 Jan 2016 15:33"));
        list.add(new FeePaymentBean("Library Fee", "8333", "18 Jan 2016 15:33"));
        list.add(new FeePaymentBean("Admission Fee", "8333", "18 Jan 2016 15:33"));
        list.add(new FeePaymentBean("Bus Fee", "8333", "18 Jan 2016 15:33"));
        list.add(new FeePaymentBean("Library Fee", "8333", "18 Jan 2016 15:33"));
        list.add(new FeePaymentBean("Admission Fee", "8333", "18 Jan 2016 15:33"));
        list.add(new FeePaymentBean("Bus Fee", "8333", "18 Jan 2016 15:33"));
        list.add(new FeePaymentBean("Library Fee", "8333", "18 Jan 2016 15:33"));
        list.add(new FeePaymentBean("Admission Fee", "8333", "18 Jan 2016 15:33"));

        adapter = new FeePaymentAdapter(FeePaymentFragment.this.getActivity(), list);
        feePaymentRV.setAdapter(adapter);
        feePaymentRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }
}
