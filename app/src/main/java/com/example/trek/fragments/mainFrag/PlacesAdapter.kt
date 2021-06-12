package com.example.trek.fragments.mainFrag

//
//class placesAdapter(context: Context,list:ArrayList<PlacesItem>):
//    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    private var con = context
//    private var li = list
//
//    override fun getItemViewType(position: Int): Int {
//        if(li.get(position).name == "BasicHome"){
//            return 0
//        }
//        else{
//            return 1
//        }
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): placesViewHolder {
//        if(viewType == 0){
//            val view:View = LayoutInflater.from(con).inflate(R.layout.basic_home_layout,parent,false)
//            return placesViewHolder(view)
//        }
//        else{
//            val view:View = LayoutInflater.from(con).inflate(R.layout.location_card,parent,false)
//            return placesViewHolder(view)
//        }
//
//    }
//
//    override fun onBindViewHolder(holder: placesViewHolder, position: Int) {
//        if(li.get(position).equals(BasicHome())){
//            Log.d("", "onBindViewHolder: kdjfd")
//        }
//        else{
//            val place:PlacesItem = li[position]
//            holder.placeName.text = place.name
//            holder.placeDetail.text = place.perex
//            Glide.with(holder.itemView.context)
//                .load(place.thumbnailUrl)
//                .into(holder.placeImage)
//
//            holder.cardParent.setOnClickListener(View.OnClickListener {
//                Log.d("tag", "onBindViewHolder: clicked")
//                val intent = Intent(it.context,ViewLocation::class.java)
//                intent.putExtra("place",place)
//                val option:ActivityOptionsCompat = ActivityOptionsCompat.
//                makeSceneTransitionAnimation(it.context as Activity,holder.placeCardImage,
//                    ViewCompat.getTransitionName(holder.placeCardImage)!!
//                )
//                it.context.startActivity(intent,option.toBundle())
//            })
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return li.size
//    }
//
//    class placesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var placeImage:ImageView = itemView.findViewById(R.id.placeImage)
//        var placeName:TextView = itemView.findViewById(R.id.placeName)
//        var placeDetail:TextView = itemView.findViewById(R.id.placeDetail)
//        var cardParent:LinearLayout = itemView.findViewById(R.id.carParent)
//        var placeCardImage:CardView = itemView.findViewById(R.id.placeCardImage)
//    }
//
//    class BasicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    }
//
////    fun setData(newList:ArrayList<PlacesItem>){
////        list = newList
////    }
//
//
//}