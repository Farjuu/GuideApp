package dev.farjana.guideapp;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class FirebaseDataHolder {
    private FirebaseRecyclerOptions<Item> options;
    List<Item> firebaseData;

    FirebaseDataHolder() {
        options = new FirebaseRecyclerOptions.Builder<Item>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("items"), Item
                        .class).build();

    }

    public FirebaseRecyclerOptions<Item> getOptions() {
        return options;
    }

    public void setOptions(FirebaseRecyclerOptions<Item> options) {
        this.options = options;
    }
}
