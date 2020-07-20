package app.brkline.and_project4_baking_app;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import app.brkline.and_project4_baking_app.databinding.FragmentStepDetailBinding;
import app.brkline.and_project4_baking_app.databinding.ToolbarBinding;
import app.brkline.and_project4_baking_app.models.RecipeStep;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StepDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StepDetailFragment extends Fragment implements Player.EventListener {

    private RecipeStep recipeStep;
    private int numberOfSteps;
    private TextView recipeStepDescriptionTextView;
    private FragmentStepDetailBinding fragmentStepDetailBinding;
    private ToolbarBinding toolbarBinding;
    private SimpleExoPlayer simpleExoPlayer;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private static final String TAG = StepDetailFragment.class.getName();
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

    // Exoplayer code and overrides below based on Google CodeLab located here:
    // https://codelabs.developers.google.com/codelabs/exoplayer-intro/#0
    private void initExoPlayer() {
        if (null == simpleExoPlayer && !(recipeStep.getVideoUrl().isEmpty())) {
            fragmentStepDetailBinding.fragmentStepDetailExoplayerPv.setVisibility(View.VISIBLE);
            DefaultTrackSelector trackSelector = new DefaultTrackSelector(getContext());
            trackSelector.setParameters(
                    trackSelector.buildUponParameters().setMaxVideoSizeSd());
            simpleExoPlayer = new SimpleExoPlayer.Builder(getContext())
                    .setTrackSelector(trackSelector)
                    .build();
            fragmentStepDetailBinding.fragmentStepDetailExoplayerPv.setPlayer(simpleExoPlayer);
            Uri uri = Uri.parse(recipeStep.getVideoUrl());
            MediaSource mediaSource = buildMediaSource(uri);
            simpleExoPlayer.setPlayWhenReady(playWhenReady);
            simpleExoPlayer.seekTo(currentWindow, playbackPosition);
            simpleExoPlayer.addListener(this);
            simpleExoPlayer.prepare(mediaSource, false, false);

//            simpleExoPlayer.addListener(this);
//            String userAgent = Util.getUserAgent(getActivity(), getString(R.string.app_name));
//            DataSource.

        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        String userAgent = Util.getUserAgent(getContext(), getString(R.string.app_name));
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(getContext(),userAgent);
        return new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initExoPlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT < 24 || null == simpleExoPlayer)) {
            initExoPlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            releaseExoPlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            releaseExoPlayer();
        }
    }

    private void hideSystemUi() {
        fragmentStepDetailBinding.fragmentStepDetailExoplayerPv.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private void releaseExoPlayer() {
        if (null != simpleExoPlayer) {
            playbackPosition = simpleExoPlayer.getCurrentPosition();
            playWhenReady = simpleExoPlayer.getPlayWhenReady();
            currentWindow = simpleExoPlayer.getCurrentWindowIndex();
            simpleExoPlayer.removeListener(this);
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady,
                                     int playbackState) {
        String stateString;
        switch (playbackState) {
            case ExoPlayer.STATE_IDLE:
                stateString = "ExoPlayer.STATE_IDLE      -";
                break;
            case ExoPlayer.STATE_BUFFERING:
                stateString = "ExoPlayer.STATE_BUFFERING -";
                break;
            case ExoPlayer.STATE_READY:
                stateString = "ExoPlayer.STATE_READY     -";
                break;
            case ExoPlayer.STATE_ENDED:
                stateString = "ExoPlayer.STATE_ENDED     -";
                break;
            default:
                stateString = "UNKNOWN_STATE             -";
                break;
        }
        Log.d(TAG, "changed state to " + stateString
                + " playWhenReady: " + playWhenReady);
    }
}