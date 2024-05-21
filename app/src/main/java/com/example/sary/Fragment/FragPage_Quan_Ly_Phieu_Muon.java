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

public class FragPage_Quan_Ly_Phieu_Muon extends Fragment { // Chuyển trang Activity sang Fragment

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStat){
        View viewPhieuMuon = inflater.inflate(R.layout.ui_frag_page_quan_ly_phieu_muon, container, false);
        return viewPhieuMuon;
    }
}