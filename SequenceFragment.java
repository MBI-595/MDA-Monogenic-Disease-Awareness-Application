package com.example.monogenicdiseases;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
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

import java.util.Timer;
import java.util.TimerTask;

public class SequenceFragment extends Fragment {
    String code;
    TextView norm,mutant,n2, n3, n4, mut2, mut3, mut4;
    Button google,google2;


    public SequenceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sequence, container, false);

        norm=(TextView) view.findViewById(R.id.NormalSequence);
        mutant=(TextView) view.findViewById(R.id.mutatedsequence);
        google=view.findViewById(R.id.button5);
        google2=view.findViewById(R.id.button6);
        n2=(TextView) view.findViewById(R.id.n2);
        n3=(TextView) view.findViewById(R.id.n3);
        n4=(TextView) view.findViewById(R.id.n4);
        mut2=(TextView) view.findViewById(R.id.mut2);
        mut3=(TextView) view.findViewById(R.id.mut3);
        mut4=(TextView) view.findViewById(R.id.mut4);

        norm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference iRef=FirebaseDatabase.getInstance().getReference("Disease");
                iRef.child("Info").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                        String name=dis.getSelected()+" Symptoms.txt";
                        code= dis.getSelected();
                        switch (code){
                            case "SCD":
                                new AlertDialog.Builder(getActivity())
                                        .setMessage("Isoelectric Point: 7.924507141113281 \n" +
                                                "AA Composition :{A=0.1254355400696864, B=0.0, C=0.010452961672473868, D=0.05226480836236934, E=0.041811846689895474, F=0.05226480836236934, G=0.06968641114982578, H=0.06620209059233449, I=0.0, J=0.0, K=0.07665505226480836, L=0.1254355400696864, M=0.010452961672473868, N=0.03484320557491289, O=0.0, P=0.04878048780487805, Q=0.013937282229965157, R=0.020905923344947737, S=0.05574912891986063, T=0.05574912891986063, U=0.0, V=0.10801393728222997, W=0.010452961672473868, X=0.0, Y=0.020905923344947737, Z=0.0, _=0.0, *=0.0, -=0.0, .=0.0}\n" +
                                                "Aliphatic Index :92.78745644599302 \n" +
                                                "Molecular Weight :30975.556300000004 \n" +
                                                "Net Charge :2.383099926899444 \n" +
                                                "Extinction :25440.0 \n")
                                        .setCancelable(true)
                                        .show();
                                break;
                            case "Cystic Fibrosis":
                                new AlertDialog.Builder(getActivity())
                                        .setMessage("Isoelectric Point: 8.877265930175781 \n" +
                                                "AA Composition :{A=0.05574210879785091, B=0.0, C=0.01208865010073875, D=0.03895231699126931, E=0.0631296171927468, F=0.0577568838146407, G=0.056413700470114174, H=0.0167897918065816, I=0.07991940899932841, J=0.0, K=0.06178643384822028, L=0.12424445936870383, M=0.024848891873740765, N=0.036937541974479515, O=0.0, P=0.03022162525184688, Q=0.04566823371390195, R=0.052384150436534584, S=0.08394895903290799, T=0.05574210879785091, U=0.0, V=0.061114842175957015, W=0.01544660846205507, X=0.0, Y=0.026863666890530557, Z=0.0, _=0.0, *=0.0, -=0.0, .=0.0} \n " +
                                                "Aliphatic Index :102.92142377434519 \n" +
                                                "Molecular Weight :169159.70710000116 \n" +
                                                "Net Charge :19.698031008048417 \n" +
                                                "Extinction :186100.0 \n")
                                        .setCancelable(true)
                                        .show();
                                break;
                            case "Hemophilia":
                                new AlertDialog.Builder(getActivity())
                                        .setMessage("Isoelectric Point: 9.302055358886719 \n" +
                                                "AA Composition :{A=0.050314465408805034, B=0.0, C=0.018867924528301886, D=0.031446540880503145, E=0.031446540880503145, F=0.050314465408805034, G=0.050314465408805034, H=0.025157232704402517, I=0.031446540880503145, J=0.0, K=0.05660377358490566, L=0.09433962264150944, M=0.03773584905660377, N=0.050314465408805034, O=0.0, P=0.050314465408805034, Q=0.0880503144654088, R=0.03773584905660377, S=0.10062893081761007, T=0.06918238993710692, U=0.0, V=0.07547169811320754, W=0.031446540880503145, X=0.0, Y=0.018867924528301886, Z=0.0, _=0.0, *=0.0, -=0.0, .=0.0} \n" +
                                                "Aliphatic Index :75.9748427672956 \n" +
                                                "Molecular Weight :18039.696700000022 \n" +
                                                "Net Charge :5.086703500087877 \n" +
                                                "Extinction: 31970.0 \n")
                                        .setCancelable(true)
                                        .show();
                                break;
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        mutant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference iRef=FirebaseDatabase.getInstance().getReference("Disease");
                iRef.child("Info").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                        code= dis.getSelected();
                        switch (code){
                            case "SCD":
                                new AlertDialog.Builder(getActivity())
                                        .setMessage("Isoelectric Point: 8.360191345214844 \n" +
                                                "AA Composition :{A=0.1254355400696864, B=0.0, C=0.010452961672473868, D=0.05226480836236934, E=0.03832752613240418, F=0.05226480836236934, G=0.06968641114982578, H=0.06620209059233449, I=0.0, J=0.0, K=0.07665505226480836, L=0.1254355400696864, M=0.010452961672473868, N=0.03484320557491289, O=0.0, P=0.04878048780487805, Q=0.013937282229965157, R=0.020905923344947737, S=0.05574912891986063, T=0.05574912891986063, U=0.0, V=0.11149825783972125, W=0.010452961672473868, X=0.0, Y=0.020905923344947737, Z=0.0, _=0.0, *=0.0, -=0.0, .=0.0}\n" +
                                                "Aliphatic Index :93.7979094076655 \n" +
                                                "Molecular Weight :30945.573400000005 \n" +
                                                "Net Charge :3.380289464926232 \n" +
                                                "Extinction :25440.0")
                                        .setCancelable(true)
                                        .show();
                                break;
                            case "Cystic Fibrosis":
                                new AlertDialog.Builder(getActivity())
                                        .setMessage("Isoelectric Point: 5.438896179199219 \n" +
                                                "AA Composition :{A=0.05172413793103448, B=0.0, C=0.017241379310344827, D=0.05172413793103448, E=0.08620689655172414, F=0.05862068965517241, G=0.07931034482758621, H=0.013793103448275862, I=0.06551724137931035, J=0.0, K=0.07241379310344828, L=0.10344827586206896, M=0.027586206896551724, N=0.04827586206896552, O=0.0, P=0.020689655172413793, Q=0.02413793103448276, R=0.04482758620689655, S=0.10344827586206896, T=0.05172413793103448, U=0.0, V=0.04482758620689655, W=0.006896551724137931, X=0.0, Y=0.027586206896551724, Z=0.0, _=0.0, *=0.0, -=0.0, .=0.0} \n" +
                                                "Aliphatic Index :84.06896551724138 \n" +
                                                "Molecular Weight :32405.861999999997 \n" +
                                                "Net Charge :-6.1826898025022174 \n" +
                                                "Extinction :22920.0 \n")
                                        .setCancelable(true)
                                        .show();
                                break;
                            case "Hemophilia":
                                new AlertDialog.Builder(getActivity())
                                        .setMessage("Isoelectric Point: 6.360038757324219 \n" +
                                                "AA Composition :{A=0.048678720445062586, B=0.0, C=0.01321279554937413, D=0.05702364394993046, E=0.0584144645340751, F=0.0521557719054242, G=0.05702364394993046, H=0.03477051460361613, I=0.05146036161335188, J=0.0, K=0.05493741307371349, L=0.08762169680111266, M=0.029902642559109873, N=0.043115438108484005, O=0.0, P=0.050764951321279554, Q=0.045201668984700974, R=0.04937413073713491, S=0.08205841446453407, T=0.056328233657858134, U=0.0, V=0.061196105702364396, W=0.019471488178025034, X=0.0, Y=0.04728789986091794, Z=0.0, _=0.0, *=0.0, -=0.0, .=0.0} \n" +
                                                "Aliphatic Index :76.8567454798331 \n" +
                                                "Molecular Weight :165288.71690000087 \n" +
                                                "Net Charge :-11.853116748234072 \n" +
                                                "Extinction :255320.0 \n")
                                        .setCancelable(true)
                                        .show();
                                break;
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference disRef=FirebaseDatabase.getInstance().getReference("Disease");
                disRef.child("Info").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                        code=dis.getSelected();
                        switch (code){
                            case "SCD":
                                Uri SCDuri= Uri.parse("https://www.rcsb.org/3d-view/1GZX");
                                startActivity(new Intent(Intent.ACTION_VIEW,SCDuri));
                                break;
                            case "Cystic Fibrosis":
                                Uri CYSuri= Uri.parse("https://www.rcsb.org/3d-view/5UAK");
                                startActivity(new Intent(Intent.ACTION_VIEW,CYSuri));
                                break;
                            case "Hemophilia":
                                Uri HEMOuri= Uri.parse("https://www.rcsb.org/3d-view/1D7P");
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

        google2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference disRef=FirebaseDatabase.getInstance().getReference("Disease");
                disRef.child("Info").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                        code=dis.getSelected();
                        switch (code){
                            case "SCD":
                                Uri SCDuri= Uri.parse("https://www.rcsb.org/3d-view/2HBS");
                                startActivity(new Intent(Intent.ACTION_VIEW,SCDuri));
                                break;
                            case "Cystic Fibrosis":
                                Uri CYSuri= Uri.parse("https://www.rcsb.org/3d-view/2BBT");
                                startActivity(new Intent(Intent.ACTION_VIEW,CYSuri));
                                break;
                            case "Hemophilia":
                                Uri HEMOuri= Uri.parse("https://www.rcsb.org/3d-view/3CDZ");
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

        norm.setMovementMethod(new ScrollingMovementMethod());

        DatabaseReference disRef=FirebaseDatabase.getInstance().getReference("Disease");
        disRef.child("Info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DiseaseModel dis=snapshot.getValue(DiseaseModel.class);
                String select=dis.getSelected();

                DatabaseReference INFO= FirebaseDatabase.getInstance().getReference("Monogenic Diseases");
                String ID="Sequences";
                switch (select){
                    case "SCD":
                        INFO.child(ID).child(select).child("norm").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                SeqModel model = snapshot.getValue(SeqModel.class);
                                norm.setText(model.getChainA());
                                n2.setText(model.getChainB());
                                n3.setText(model.getChainC());
                                n4.setText(model.getChainD());
//                                n2.setText(" >pdb|1GZX|B Chain B, HEMOGLOBIN BETA CHAIN                                                                                                          VHLTPEEKSAVTALWGKVNVDEVGGEALGRLLVVYPWTQRFFESFGDLSTPDAVMGNPKVKAHGKKVLGA FSDGLAHLDNLKGTFATLSELHCDKLHVDPENFRLLGNVLVCVLAHHFGKEFTPPVQAAYQKVVAGVANA LAHKYH");
//                                n3.setText(">pdb|1GZX|C Chain C, HEMOGLOBIN ALPHA CHAIN                                                                                                                                            VLSPADKTNVKAAWGKVGAHAGEYGAEALERMFLSFPTTKTYFPHFDLSHGSAQVKGHGKKVADALTNAV AHVDDMPNALSALSDLHAHKLRVDPVNFKLLSHCLLVTLAAHLPAEFTPAVHASLDKFLASVSTVLTSKY R");
//                                n4.setText(">pdb|1GZX|D Chain D, HEMOGLOBIN BETA CHAIN                                                                                                                               VHLTPEEKSAVTALWGKVNVDEVGGEALGRLLVVYPWTQRFFESFGDLSTPDAVMGNPKVKAHGKKVLGA FSDGLAHLDNLKGTFATLSELHCDKLHVDPENFRLLGNVLVCVLAHHFGKEFTPPVQAAYQKVVAGVANA LAHKYH");
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        INFO.child(ID).child(select).child("mutant").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                SeqModel model = snapshot.getValue(SeqModel.class);
                                mutant.setText(model.getChainA());
                                mut2.setText(model.getChainB());
                                mut3.setText(model.getChainC());
                                mut4.setText(model.getChainD());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        break;
                    case "Cystic Fibrosis":
                    case "Hemophilia":
                        INFO.child(ID).child(select).child("norm").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                SeqModel model = snapshot.getValue(SeqModel.class);
                                norm.setText(model.getChainA());
                                n2.setVisibility(View.GONE);
                                n3.setVisibility(View.GONE);
                                n4.setVisibility(View.GONE);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        INFO.child(ID).child(select).child("mutant").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                SeqModel model = snapshot.getValue(SeqModel.class);
                                mutant.setText(model.getChainA());
                                mut2.setText(model.getChainB());
                                mut3.setVisibility(View.GONE);
                                mut4.setVisibility(View.GONE);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        break;

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}