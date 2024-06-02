package com.example.sary.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sary.R;

public class FragPage_ThongKeDoanhThu extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View viewThanhVien = inflater.inflate(R.layout.ui_frag_page_thong_ke_doanh_thu, container, false);
        return viewThanhVien;
    }
}