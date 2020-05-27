package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuoteServiceImpl implements QuoteService {

	@Autowired
	private QuoteRepository repository;

	@Override
	public Quote getQuote() {
		return getRandomQuote(repository.findAll());
	}

	@Override
	public Quote getQuoteByActor(String actor) {

		return getRandomQuote(repository.findByActor(actor));
	}

	public Quote getRandomQuote(List<Quote> quotes){
		return quotes.get(new Random().nextInt(quotes.size()));
	}

}
