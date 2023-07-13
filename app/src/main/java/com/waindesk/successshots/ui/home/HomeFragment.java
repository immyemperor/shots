package com.waindesk.successshots.ui.home;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.squareup.picasso.Picasso;
import com.waindesk.successshots.R;
import com.waindesk.successshots.autoplay.CustomRecyclerView;
import com.waindesk.successshots.databinding.FragmentHomeBinding;
import com.waindesk.successshots.videoutils.AutoPlayVideoAdapter;
import com.waindesk.successshots.videoutils.VideoModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    CustomRecyclerView recyclerView;
    @BindView(com.waindesk.successshots.R.id.rv_home)
    public List<VideoModel> modelList = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.rvHome;
        ButterKnife.bind(this.getActivity());

        Picasso p = Picasso.with(this.getActivity());
        modelList = setMediaSource();
        AutoPlayVideoAdapter mAdapter = new AutoPlayVideoAdapter(modelList,p);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//todo before setAdapter
        recyclerView.setActivity(this.getActivity());

        //optional - to play only first visible video
        recyclerView.setPlayOnlyFirstVideo(true); // false by default

        //optional - by default we check if url ends with ".mp4". If your urls do not end with mp4, you can set this param to false and implement your own check to see if video points to url
        recyclerView.setCheckForMp4(false); //true by default

        //optional - download videos to local storage (requires "android.permission.WRITE_EXTERNAL_STORAGE" in manifest or ask in runtime)
        recyclerView.setDownloadPath(Environment.getExternalStorageDirectory() + "/MyVideo"); // (Environment.getExternalStorageDirectory() + "/Video") by default

        recyclerView.setDownloadVideos(true); // false by default

        List<String> urls = new ArrayList<>();
        for (VideoModel object : modelList) {
            if (object.getVideo_url() != null && object.getVideo_url().contains("http"))
                urls.add(object.getVideo_url());
        }
        recyclerView.preDownload(urls);

        recyclerView.setAdapter(mAdapter);
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private List<VideoModel> setMediaSource() {
        List<VideoModel> list = new ArrayList<>();
        list.add(
                new VideoModel(
                        "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4",
                        "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png",
                        "Description for media object #1")
        );
        list.add(
                new VideoModel(
                        "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/REST+API+Retrofit+MVVM+Course+Summary.mp4",
                        "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/REST+API%2C+Retrofit2%2C+MVVM+Course+SUMMARY.png",
                        "Description for media object #2")
        );
        list.add(
                new VideoModel(
                        "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/MVVM+and+LiveData+for+youtube.mp4",
                        "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/mvvm+and+livedata.png",
                        "Description for media object #3")
        );
        list.add(
                new VideoModel(
                        "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/SwipingViewPager+Tutorial.mp4",
                        "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Swiping+Views+with+a+ViewPager.png",
                        "Description for media object #4")
        );
        list.add(
                new VideoModel(
                        "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Rest+api+teaser+video.mp4",
                        "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Rest+API+Integration+with+MVVM.png",
                        "Description for media object #5")
        );
        list.add(
                new VideoModel(
                        "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Rest+api+teaser+video.mp4",
                        "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Rest+API+Integration+with+MVVM.png",
                        "Description for media object #6")
        );
        list.add(
                new VideoModel(
                        "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Rest+api+teaser+video.mp4",
                        "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Rest+API+Integration+with+MVVM.png",
                        "Description for media object #7")
        );
        list.add(
                new VideoModel(
                        "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Rest+api+teaser+video.mp4",
                        "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Rest+API+Integration+with+MVVM.png",
                        "Description for media object #8")
        );
        list.add(
                new VideoModel(
                        "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Rest+api+teaser+video.mp4",
                        "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Rest+API+Integration+with+MVVM.png",
                        "Description for media object #9")
        );
        return list;
    }

    @Override
    public void onStop() {
        super.onStop();
        //add this code to pause videos (when app is minimised or paused)
        recyclerView.stopVideos();
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.playAvailableVideos(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}