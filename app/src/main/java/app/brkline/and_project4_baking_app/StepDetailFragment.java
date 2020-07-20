package app.brkline.and_project4_baking_app;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.SimpleExoPlayer.Builder;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import

import javax.sql.DataSource;

import app.brkline.and_project4_baking_app.databinding.FragmentStepDetailBinding;
import app.brkline.and_project4_baking_app.databinding.ToolbarBinding;
import app.brkline.and_project4_baking_app.models.RecipeStep;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StepDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StepDetailFragment extends Fragment implements Player.EventListener  {

    private RecipeStep recipeStep;
    private int numberOfSteps;
    private TextView recipeStepDescriptionTextView;
    private FragmentStepDetailBinding fragmentStepDetailBinding;
    private ToolbarBinding toolbarBinding;
    private SimpleExoPlayer simpleExoPlayer;
    private PlayerView playerView;
//    OnStepClickListener onStepClickListener;

    public StepDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param recipeStep Recipe Step.
     * @param numberOfSteps Total Number of Steps.
     * @return A new instance of fragment StepDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StepDetailFragment newInstance(RecipeStep recipeStep, int numberOfSteps) {
        StepDetailFragment fragment = new StepDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.STEP_EXTRA, recipeStep);
        args.putInt(Constants.STEP_NUMBER_OF_STEPS, numberOfSteps);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onClick(View view) {
//        int viewId = view.getId();
//        switch (viewId) {
//            case R.id.fragment_step_detail_prev_btn:
//                onStepClickListener.onPrevStepClick(recipeStep);
//                break;
//            case R.id.fragment_step_detail_next_btn:
//                onStepClickListener.onNextStepClick(recipeStep);
//                break;
//        }
//    }

//    public interface OnStepClickListener {
//        void onPrevStepClick(RecipeStep recipeStep);
//        void onNextStepClick(RecipeStep recipeStep);
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            recipeStep = getArguments().getParcelable(Constants.STEP_EXTRA);
            numberOfSteps = getArguments().getInt(Constants.STEP_NUMBER_OF_STEPS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentStepDetailBinding = FragmentStepDetailBinding.inflate(inflater, container, false);
        fragmentStepDetailBinding.activityStepDetailStepDescriptionTv.setText(recipeStep.getDescription());
//        fragmentStepDetailBinding.activityStepDetailStepShortDescriptionTv.setText(recipeStep.getShortDescription());
//        recipeStepDescriptionTextView = fragmentStepDetailBinding.(R.id.activity_step_detail_step_description_tv);
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_step_detail, container, false);
//        fragmentStepDetailBinding.activtityStepDetailBottomNav.setOnClickListener(container);
//        fragmentStepDetailBinding.fragmentStepDetailPrevBtn.setOnClickListener(this);
//        fragmentStepDetailBinding.fragmentStepDetailNextBtn.setOnClickListener(this);
        toolbarBinding = fragmentStepDetailBinding.fragmentStepIncludeToolbar;
//        String stepOfTotal = (recipeStep.getId() + 1) + " of " + numberOfSteps;
//        fragmentStepDetailBinding.fragmentStepDetailStepNumberTv.setText(stepOfTotal);
        return fragmentStepDetailBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        initToolbar();
    }

    private void initToolbar() {

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbarBinding.toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        toolbarBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    private void initExoPlayer() {
        if (null == simpleExoPlayer && !(recipeStep.getVideoUrl().isEmpty())) {
            fragmentStepDetailBinding.fragmentStepDetailExoplayerPv.setVisibility(View.VISIBLE);

            simpleExoPlayer = new SimpleExoPlayer.Builder(getContext()).build();
            fragmentStepDetailBinding.fragmentStepDetailExoplayerPv.setPlayer(simpleExoPlayer);

//            simpleExoPlayer.addListener(this);
//            String userAgent = Util.getUserAgent(getActivity(), getString(R.string.app_name));
//            DataSource.

        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        String userAgent = Util.getUserAgent(getContext(), getString(R.string.app_name));
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(getContext(),userAgent);
        return new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
    }
}