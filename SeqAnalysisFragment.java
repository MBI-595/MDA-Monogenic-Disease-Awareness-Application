package com.example.monogenicdiseases;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SeqAnalysisFragment extends Fragment {
    Button pBLAST, mBLAST, Omega;
    TextView mole,mmole;

    public SeqAnalysisFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_seq_analysis, container, false);

        pBLAST=(Button) view.findViewById(R.id.pair_align);
        mBLAST=(Button) view.findViewById(R.id.multiple_align);
        Omega=(Button) view.findViewById(R.id.clustal);
        mole=(TextView) view.findViewById(R.id.n_mol);
        mmole=(TextView) view.findViewById(R.id.m_mol);

        DatabaseReference disRef= FirebaseDatabase.getInstance().getReference("Disease");
        disRef.child("Info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                String select=dis.getSelected();

                switch (select) {
                    case "SCD":
                        mole.setText("REFERENCE SEQUENCE CHAINS:\n \n" +
                                "chain 0: >A< HEMOGLOBIN ALPHA CHAIN length SEQRES: 0 length ATOM: 205 aminos: 141 hetatms: 64 nucleotides: 0 \n" +
                                "chain 1: >B< HEMOGLOBIN BETA CHAIN length SEQRES: 0 length ATOM: 203 aminos: 146 hetatms: 57 nucleotides: 0 \n" +
                                "chain 2: >C< HEMOGLOBIN ALPHA CHAIN length SEQRES: 0 length ATOM: 188 aminos: 141 hetatms: 47 nucleotides: 0 \n" +
                                "chain 3: >D< HEMOGLOBIN BETA CHAIN length SEQRES: 0 length ATOM: 191 aminos: 146 hetatms: 45 nucleotides: 0");
                        mmole.setText("MUTATED SEQUENCE CHAINS: \n \n" +
                                "chain 0: >A< HEMOGLOBIN ALPHA CHAIN length SEQRES: 0 length ATOM: 205 aminos: 141 hetatms: 64 nucleotides: 0 \n" +
                                "chain 1: >B< HEMOGLOBIN BETA CHAIN length SEQRES: 0 length ATOM: 203 aminos: 146 hetatms: 57 nucleotides: 0 \n" +
                                "chain 2: >C< HEMOGLOBIN ALPHA CHAIN length SEQRES: 0 length ATOM: 188 aminos: 141 hetatms: 47 nucleotides: 0 \n" +
                                "chain 3: >D< HEMOGLOBIN BETA CHAIN length SEQRES: 0 length ATOM: 191 aminos: 146 hetatms: 45 nucleotides: 0");
                        break;
                    case "Cystic Fibrosis":
                        mole.setText("REFERENCE SEQUENCE CHAINS:\n \n" +
                                "Chains:chain 0: >A< CYSTIC FIBROSIS TRANSMEMBRANE CONDUCTANCE REGULATOR length SEQRES: 0 length ATOM: 1139 aminos: 1139 hetatms: 0 nucleotides: 0 \n" +
                                "chain 1: >R< CYSTIC FIBROSIS TRANSMEMBRANE CONDUCTANCE REGULATOR length SEQRES: 0 length ATOM: 19 aminos: 0 hetatms: 19 nucleotides: 0");
                        mmole.setText("MUTATED SEQUENCE CHAINS: \n \n" +
                                "chain 0: >A< CYSTIC FIBROSIS TRANSMEMBRANE CONDUCTANCE REGULATOR length SEQRES: 0 length ATOM: 320 aminos: 249 hetatms: 71 nucleotides: 0 \n" +
                                "chain 1: >B< CYSTIC FIBROSIS TRANSMEMBRANE CONDUCTANCE REGULATOR length SEQRES: 0 length ATOM: 320 aminos: 258 hetatms: 62 nucleotides: 0");
                        break;
                    case "Hemophilia":
                        mole.setText("REFERENCE SEQUENCE CHAINS:\n \n" +
                                "chain 0: >M< COAGULATION FACTOR VIII PRECURSOR length SEQRES: 0 length ATOM: 410 aminos: 160 hetatms: 250 nucleotides: 0");
                        mmole.setText("MUTATED SEQUENCE CHAINS: \n \n" +
                                "chain 0: >A< COAGULATION FACTOR VIII HEAVY CHAIN length SEQRES: 0 length ATOM: 632 aminos: 630 hetatms: 2 nucleotides: 0 \n" +
                                "chain 1: >B< COAGULATION FACTOR VIII LIGHT CHAIN length SEQRES: 0 length ATOM: 634 aminos: 631 hetatms: 3 nucleotides: 0 \n" +
                                "chain 2: >C<  length SEQRES: 0 length ATOM: 2 aminos: 0 hetatms: 2 nucleotides: 0 \n" +
                                "chain 3: >D<  length SEQRES: 0 length ATOM: 4 aminos: 0 hetatms: 4 nucleotides: 0");
                        break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        pBLAST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference disRef= FirebaseDatabase.getInstance().getReference("Disease");
                disRef.child("Info").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                        String select=dis.getSelected();

                        switch (select) {
                            case "SCD":
                                Uri SCDuri= Uri.parse("https://blast.ncbi.nlm.nih.gov/Blast.cgi?CMD=Get&RID=EWRF18T5114#alnHdr_Query_34331");
                                startActivity(new Intent(Intent.ACTION_VIEW,SCDuri));
                                break;
                            case "Cystic Fibrosis":
                                Uri CFuri= Uri.parse("https://blast.ncbi.nlm.nih.gov/Blast.cgi?CMD=Get&RID=EWTPU4KU114&OLD_BLAST=false");
                                startActivity(new Intent(Intent.ACTION_VIEW,CFuri));
                                break;
                            case "Hemophilia":
                                Uri HEMOuri= Uri.parse("https://blast.ncbi.nlm.nih.gov/Blast.cgi?CMD=Get&RID=EZ0UN9AP114");
                                startActivity(new Intent(Intent.ACTION_VIEW,HEMOuri));
                                break;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        mBLAST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference disRef= FirebaseDatabase.getInstance().getReference("Disease");
                disRef.child("Info").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                        String select=dis.getSelected();

                        switch (select) {
                            case "SCD":
                                Uri SCDuri= Uri.parse("https://www.ncbi.nlm.nih.gov/projects/msaviewer/?rid=EWRF18T5114&coloring=cons");
                                startActivity(new Intent(Intent.ACTION_VIEW,SCDuri));
                                break;
                            case "Cystic Fibrosis":
                                Uri CFuri= Uri.parse("https://www.ncbi.nlm.nih.gov/projects/msaviewer/?rid=EWTPU4KU114&coloring=cons");
                                startActivity(new Intent(Intent.ACTION_VIEW,CFuri));
                                break;
                            case "Hemophilia":
                                Uri HEMOuri= Uri.parse("https://www.ncbi.nlm.nih.gov/tools/cobalt/cobalt.cgi");
                                startActivity(new Intent(Intent.ACTION_VIEW,HEMOuri));
                                break;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        Omega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference disRef= FirebaseDatabase.getInstance().getReference("Disease");
                disRef.child("Info").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                        String select=dis.getSelected();

                        switch (select) {
                            case "SCD":
                                Uri SCDuri= Uri.parse("https://www.ebi.ac.uk/Tools/services/web/toolresult.ebi?jobId=clustalo-I20220806-135741-0247-98937653-p2m&showColors=true&tool=clustalo");
                                startActivity(new Intent(Intent.ACTION_VIEW,SCDuri));
                                break;
                            case "Cystic Fibrosis":
                                Uri CFuri= Uri.parse("https://www.ebi.ac.uk/Tools/services/web/toolresult.ebi?jobId=clustalo-I20220806-140540-0965-8217973-p1m&showColors=true&tool=clustalo");
                                startActivity(new Intent(Intent.ACTION_VIEW,CFuri));
                                break;
                            case "Hemophilia":
                                Uri HEMOuri= Uri.parse("https://www.ebi.ac.uk/Tools/services/web/toolresult.ebi?jobId=clustalo-I20220806-142938-0510-4632790-p2m&showColors=true&tool=clustalo");
                                startActivity(new Intent(Intent.ACTION_VIEW,HEMOuri));
                                break;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        return view;
    }
}