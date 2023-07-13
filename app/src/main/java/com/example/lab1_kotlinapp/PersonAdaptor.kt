import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lab1_kotlinapp.Book
import com.example.lab1_kotlinapp.R

class PersonAdapter(private val personList: MutableList<Book>) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtRole: TextView = itemView.findViewById(R.id.txtRole)
        val imageView: ImageView = itemView.findViewById(R.id.ImageView)

        fun bind(Book: Book) {
            txtName.text = Book.title
            txtRole.text = Book.author
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
