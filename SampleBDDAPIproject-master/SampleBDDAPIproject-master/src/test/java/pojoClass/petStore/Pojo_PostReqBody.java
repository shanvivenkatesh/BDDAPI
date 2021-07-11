package pojoClass.petStore;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Pojo_PostReqBody {

	public static Pojo_PetStore_Post_Request petOrderPost;
	
	public Pojo_PetStore_Post_Request postValidRequest() {
		
		//generating the shipDate dynamically for every request
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.UK);
		format.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = new Date();
		String strDate = format.format(date);
		
		petOrderPost = new Pojo_PetStore_Post_Request();

		petOrderPost.setId(29744);
		petOrderPost.setPetId(374334);
		petOrderPost.setQuantity(10);
		petOrderPost.setShipDate(strDate);
		petOrderPost.setStatus("placed");
		petOrderPost.setComplete(true);

		return petOrderPost;

	}

	public Pojo_PetStore_Post_Request postInvalidRequest() {

		Pojo_PetStore_Post_Request petOrderPost = new Pojo_PetStore_Post_Request();
		petOrderPost.setId(003774);
		petOrderPost.setPetId(374334);
		petOrderPost.setQuantity(10);
		petOrderPost.setShipDate("2021-07-09T11:56:30.604Z");
		petOrderPost.setStatus("placed");
		petOrderPost.setComplete(true);

		return petOrderPost;
	}

}
