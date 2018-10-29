//package com.example.shawnli.pickupball;
//
//import android.content.Context;
//import android.graphics.drawable.Drawable;
//import android.support.annotation.NonNull;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
//import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
//import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
//import com.bignerdranch.expandablerecyclerview.model.Parent;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class test {
//
//    public class Group implements Parent<Object> {
//
//        private String groupName;
//        private ArrayList<Object> mChildrenList;
//
//        Group(String name, ArrayList<Object> items) {
//            this.groupName = name;
//            this.mChildrenList = items;
//        }
//
//
//        @Override
//        public List<Object> getChildList() {
//            return mChildrenList;
//        }
//
//        @Override
//        public boolean isInitiallyExpanded() {
//            return false;
//        }
//    }
//
//    class Adapter extends ExpandableRecyclerAdapter<Group, Object, GroupHolder, Holder> {
//
//        private LayoutInflater inflater;
//
//        public Adapter(Context context, List<Group> groups) {
//            super(groups);
//            inflater = LayoutInflater.from(context);
//        }
//
//        @Override
//        public GroupHolder onCreateParentViewHolder(@NonNull ViewGroup viewGroup, int i) {
//            View groupView = inflater.inflate(R.layout.person_list_group, viewGroup, false);
//            return new GroupHolder(groupView);
//        }
//
//        @Override
//        public Holder onCreateChildViewHolder(@NonNull ViewGroup viewGroup, int i) {
//            View holderView = inflater.inflate(R.layout.person_list_item, viewGroup, false);
//            return new Holder(holderView);
//        }
//
//        @Override
//        public void onBindParentViewHolder(@NonNull GroupHolder holder, int i, Group group) {
//            holder.bind(group);
//        }
//
//        @Override
//        public void onBindChildViewHolder(@NonNull Holder holder, int i, int j, Object item) {
//            holder.bind(item);
//        }
//
//    }
//
//    class GroupHolder extends ParentViewHolder {
//
//        private TextView mGroupName;
//
//        public GroupHolder(View itemView) {
//            super(itemView);
//            mGroupName = (TextView) itemView.findViewById(R.id.group_title);
//        }
//
//        void bind(Group group) {
//            mGroupName.setText(group.groupName);
//        }
//
//    }
//
//    class Holder extends ChildViewHolder {
//
//        private Object currentObject;
//        private TextView mFirstLine;
//        private TextView mSecondLine;
//        private ImageView mIcon;
//        private Person familyMember;
//        private String eventInfo;
//        private String fullName;
//
//
//        public Holder(View view) {
//            super(view);
//            mFirstLine = (TextView) view.findViewById(R.id.textView1);
//            mSecondLine = (TextView) view.findViewById(R.id.textView2);
//            mIcon = (ImageView) view.findViewById(R.id.eventOrPersonIcon);
//
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (currentObject != null) {
//                        if (currentObject instanceof Person){
//
//                            //Start Person Activity
//                            familyMember = (Person) currentObject;
//                            DataHolder.getInstance().setCurrentPerson(familyMember);
//                            DataHolder.getInstance().setGender(familyMember.getGender());
//                            setUpActivity(PersonActivity.class);
//                        }
//
//                        if (currentObject instanceof Event) {
//                            currEvent = (Event) currentObject;
//
//                            //Start MAP Activity
//                            DataHolder.getInstance().setCurrentEvent(currEvent);
//                            DataHolder.getInstance().setEventInfo(eventInfo);
//                            DataHolder.getInstance().setPersonInfo(fullName);
//                            setUpActivity(MapActivity.class);
//                        }
//                    }
//                }
//            });
//        }
//
//        void bind(Object item) {
//            this.currentObject = item;
//
//            if(currentObject instanceof Event)
//            {
//                currEvent = (Event) currentObject;
//
//                eventInfo = currEvent.getEventType() + ": " + currEvent.getCity() + ", " +
//                        currEvent.getCountry() + " ("+ currEvent.getYear() + ")";
//
//                fullName = clickedPerson.getFirstName() + " " + clickedPerson.getLastName();
//
//                Drawable eventIcon = new IconDrawable(getBaseContext(), FontAwesomeIcons.fa_map_marker).
//                        colorRes(R.color.event_icon).sizeDp(30);
//
//                mFirstLine.setText(eventInfo);
//                mSecondLine.setText(fullName);
//                mIcon.setImageDrawable(eventIcon);
//
//            }
//            else if(currentObject instanceof Person) {
//
//                familyMember = (Person) currentObject;
//                String familyMemberName = familyMember.getFirstName() + " " + familyMember.getLastName();
//
//                mFirstLine.setText(familyMemberName);
//
//
//                Drawable femaleIcon = new IconDrawable(getBaseContext(), FontAwesomeIcons.fa_female).
//                        colorRes(R.color.female_icon).sizeDp(30);
//                Drawable maleIcon = new IconDrawable(getBaseContext(), FontAwesomeIcons.fa_male).
//                        colorRes(R.color.male_icon).sizeDp(30);
//
//                String relationship = DataHolder.getInstance().checkRelationship(familyMember);
//                mSecondLine.setText(relationship);
//
//                if(familyMember.getGender().equals(F)) {
//                    mIcon.setImageDrawable(femaleIcon);
//                }
//
//                if(familyMember.getGender().equals(M)) {
//                    mIcon.setImageDrawable(maleIcon);
//                }
//            }
//        }
//    }
//
//}
