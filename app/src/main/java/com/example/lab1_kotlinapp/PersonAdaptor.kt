import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lab1_kotlinapp.*

class PersonAdapter(private val personList: MutableList<Book>) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtRole: TextView = itemView.findViewById(R.id.txtRole)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)


        init {
            // Set click listener for the imageView
            imageView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = personList[position]

                    // Create an Intent to start the new activity
                    val intent = Intent(itemView.context, BookDisplayActivity::class.java)

                    // Pass any extra data to the new activity if needed
                    intent.putExtra("bookId", clickedItem.id)


                    // Start the new activity
                    itemView.context.startActivity(intent)
                }
            }
        }

        fun bind(Book: Book) {
            txtName.text = Book.title
            txtRole.text = Book.author
            imageView.tag = Book.id
            // Load the photo image using Glide
            Glide.with(itemView)
                .load(Book.imageLink)
                .into(imageView)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return PersonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val currentItem = personList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    fun setPersonList(newPersonList: MutableList<Book>) {
        personList.clear()
        personList.addAll(newPersonList)
        notifyDataSetChanged()
    }


}
