package com.example.manishdwibedy.stockmarketviewer.util;

import android.util.Log;

import com.example.manishdwibedy.stockmarketviewer.model.stock.feednews.gson.StockNews;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by manishdwibedy on 3/20/16.
 */
public class GetStockNews {
    private static final String TAG = "GetStockNews";

    public static StockNews getStockNewsFeed(String symbol){

        StockNews stockData = null;
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String baseURL = "https://stock-app-csci.appspot.com/api.php?operation=news&symbol=";

        String response;
        try
        {
            response = HandleGETRequests.sendGet(baseURL + symbol);

            stockData = gson.fromJson(response, StockNews.class);

            Log.d(TAG, "Response Status - "+stockData.getResponseStatus());
            if(stockData.getResponseStatus() != 200)
            {
                String json = "{\"responseData\": {\"results\":[{\"GsearchResultClass\":\"GnewsSearch\",\"clusterUrl\":\"http://news.google.com/news/story?ncl\\u003ddIQyK2tKjqYbQ8MMP0eBb28toolzM\\u0026hl\\u003den\\u0026ned\\u003dus\",\"content\":\"Kevin Turner, the former NFL fullback with ALS who was a lead plaintiff in a landmark concussion lawsuit against the league, died Thursday morning. He was 46. Turner played for the University of Alabama prior to his eight-years in the NFL that included \\u003cb\\u003e...\\u003c/b\\u003e\",\"unescapedUrl\":\"http://www.nydailynews.com/sports/football/nfl-fb-kevin-turner-dies-46-battle-als-article-1.2576628\",\"url\":\"http%3A%2F%2Fwww.nydailynews.com%2Fsports%2Ffootball%2Fnfl-fb-kevin-turner-dies-46-battle-als-article-1.2576628\",\"title\":\"Former NFL fullback Kevin Turner dies at 46 after battle with ALS\",\"titleNoFormatting\":\"Former NFL fullback Kevin Turner dies at 46 after battle with ALS\",\"location\":\"\",\"publisher\":\"New York Daily News\",\"publishedDate\":\"Thu, 24 Mar 2016 11:51:58 -0700\",\"signedRedirectUrl\":\"http://news.google.com/news/url?sa\\u003dT\\u0026ct2\\u003dus\\u0026fd\\u003dS\\u0026url\\u003dhttp://www.nydailynews.com/sports/football/nfl-fb-kevin-turner-dies-46-battle-als-article-1.2576628\\u0026cid\\u003d52779068622580\\u0026ei\\u003dpJT0VsjkKqbwwQG0pKE4\\u0026usg\\u003dAFQjCNGeIadGJSn_tpl5PnZLkHVbT1AK8A\",\"language\":\"en\",\"image\":{\"url\":\"http://assets.nydailynews.com/polopoly_fs/1.2576633.1458845268!/img/httpImage/image.jpg_gen/derivatives/article_635/nfl-concussion-lawsuits-football.jpg\",\"tbUrl\":\"http://t3.gstatic.com/images?q\\u003dtbn:ANd9GcSXEr-8wbT32SoBsOhzIuPrtcyNpkF-Zg4USfGHDm3_6_lknyf0kXn2uMk\",\"originalContextUrl\":\"http://www.nydailynews.com/sports/football/nfl-fb-kevin-turner-dies-46-battle-als-article-1.2576628\",\"publisher\":\"New York Daily News\",\"tbWidth\":80,\"tbHeight\":53},\"relatedStories\":[{\"unescapedUrl\":\"http://www.csnphilly.com/football-philadelphia-eagles/former-eagles-fb-kevin-turner-dies-46-following-als-battle\",\"url\":\"http%3A%2F%2Fwww.csnphilly.com%2Ffootball-philadelphia-eagles%2Fformer-eagles-fb-kevin-turner-dies-46-following-als-battle\",\"title\":\"Former Eagles \\u003cb\\u003eFB\\u003c/b\\u003e Kevin Turner dies at 46 following ALS battle\",\"titleNoFormatting\":\"Former Eagles fb Kevin Turner dies at 46 following ALS battle\",\"location\":\"\",\"publisher\":\"Comcast SportsNet Philadelphia\",\"publishedDate\":\"Thu, 24 Mar 2016 11:39:08 -0700\",\"signedRedirectUrl\":\"http://news.google.com/news/url?sa\\u003dT\\u0026ct2\\u003dus\\u0026fd\\u003dS\\u0026url\\u003dhttp://www.csnphilly.com/football-philadelphia-eagles/former-eagles-fb-kevin-turner-dies-46-following-als-battle\\u0026cid\\u003d52779068622580\\u0026ei\\u003dpJT0VsjkKqbwwQG0pKE4\\u0026usg\\u003dAFQjCNHl9RK2sueSCrdag5EqwKA3md2VjQ\",\"language\":\"en\"},{\"unescapedUrl\":\"http://sports.yahoo.com/blogs/nfl-shutdown-corner/former-patriots--eagles-fb-kevin-turner-dies-after-battle-with-als-192048010.html\",\"url\":\"http%3A%2F%2Fsports.yahoo.com%2Fblogs%2Fnfl-shutdown-corner%2Fformer-patriots--eagles-fb-kevin-turner-dies-after-battle-with-als-192048010.html\",\"title\":\"Former Patriots, Eagles \\u003cb\\u003eFB\\u003c/b\\u003e Kevin Turner dies after battle with ALS\",\"titleNoFormatting\":\"Former Patriots, Eagles fb Kevin Turner dies after battle with ALS\",\"location\":\"\",\"publisher\":\"Yahoo Sports (blog)\",\"publishedDate\":\"Thu, 24 Mar 2016 12:18:45 -0700\",\"signedRedirectUrl\":\"http://news.google.com/news/url?sa\\u003dT\\u0026ct2\\u003dus\\u0026fd\\u003dS\\u0026url\\u003dhttp://sports.yahoo.com/blogs/nfl-shutdown-corner/former-patriots--eagles-fb-kevin-turner-dies-after-battle-with-als-192048010.html\\u0026cid\\u003d52779068622580\\u0026ei\\u003dpJT0VsjkKqbwwQG0pKE4\\u0026usg\\u003dAFQjCNFmqkfgJ_WXpDA9r0SPUUoARTSzWg\",\"language\":\"en\"},{\"unescapedUrl\":\"http://www.wptz.com/sports/former-nfl-alabama-fb-turner-dies-at-46/38679480\",\"url\":\"http%3A%2F%2Fwww.wptz.com%2Fsports%2Fformer-nfl-alabama-fb-turner-dies-at-46%2F38679480\",\"title\":\"Former NFL, Alabama \\u003cb\\u003eFB\\u003c/b\\u003e Turner dies at 46\",\"titleNoFormatting\":\"Former NFL, Alabama fb Turner dies at 46\",\"location\":\"\",\"publisher\":\"WPTZ\",\"publishedDate\":\"Thu, 24 Mar 2016 14:00:00 -0700\",\"signedRedirectUrl\":\"http://news.google.com/news/url?sa\\u003dT\\u0026ct2\\u003dus\\u0026fd\\u003dS\\u0026url\\u003dhttp://www.wptz.com/sports/former-nfl-alabama-fb-turner-dies-at-46/38679480\\u0026cid\\u003d52779068622580\\u0026ei\\u003dpJT0VsjkKqbwwQG0pKE4\\u0026usg\\u003dAFQjCNG-pK7LpPDw5vyvZnB_kIqiAvTORQ\",\"language\":\"en\"},{\"unescapedUrl\":\"http://247sports.com/Bolt/Former-Eagles-fb-Kevin-Turner-passes-away-44453950\",\"url\":\"http%3A%2F%2F247sports.com%2FBolt%2FFormer-Eagles-fb-Kevin-Turner-passes-away-44453950\",\"title\":\"Kevin Turner passes away after battle with ALS\",\"titleNoFormatting\":\"Kevin Turner passes away after battle with ALS\",\"location\":\"\",\"publisher\":\"247Sports\",\"publishedDate\":\"Thu, 24 Mar 2016 12:52:30 -0700\",\"signedRedirectUrl\":\"http://news.google.com/news/url?sa\\u003dT\\u0026ct2\\u003dus\\u0026fd\\u003dS\\u0026url\\u003dhttp://247sports.com/Bolt/Former-Eagles-fb-Kevin-Turner-passes-away-44453950\\u0026cid\\u003d52779068622580\\u0026ei\\u003dpJT0VsjkKqbwwQG0pKE4\\u0026usg\\u003dAFQjCNEy1phBdz0n10_QbW5LTemIMyekwA\",\"language\":\"en\"},{\"unescapedUrl\":\"http://www.csnne.com/new-england-patriots/former-patriots-fb-turner-dies-after-battle-als\",\"url\":\"http%3A%2F%2Fwww.csnne.com%2Fnew-england-patriots%2Fformer-patriots-fb-turner-dies-after-battle-als\",\"title\":\"Life and death of Turner carries meaning, poignancy\",\"titleNoFormatting\":\"Life and death of Turner carries meaning, poignancy\",\"location\":\"\",\"publisher\":\"Comcast SportsNet New England\",\"publishedDate\":\"Thu, 24 Mar 2016 13:33:09 -0700\",\"signedRedirectUrl\":\"http://news.google.com/news/url?sa\\u003dT\\u0026ct2\\u003dus\\u0026fd\\u003dS\\u0026url\\u003dhttp://www.csnne.com/new-england-patriots/former-patriots-fb-turner-dies-after-battle-als\\u0026cid\\u003d52779068622580\\u0026ei\\u003dpJT0VsjkKqbwwQG0pKE4\\u0026usg\\u003dAFQjCNEdG4LGYVxJW9ZGS9GzQ87GTMduIw\",\"language\":\"en\"}]},{\"GsearchResultClass\":\"GnewsSearch\",\"clusterUrl\":\"\",\"content\":\"Sure, \\u003cb\\u003eFB\\u003c/b\\u003e is still growing users at a faster pace than Twitter Inc (TWTR), which is extremely impressive since Facebook is five times larger than Twitter. Decelerating user growth, however, seems inevitable at this point; there are only so many people \\u003cb\\u003e...\\u003c/b\\u003e\",\"unescapedUrl\":\"http://investorplace.com/2016/03/facebook-fb-stock-china/\",\"url\":\"http%3A%2F%2Finvestorplace.com%2F2016%2F03%2Ffacebook-fb-stock-china%2F\",\"title\":\"Facebook Inc: Will \\u003cb\\u003eFB\\u003c/b\\u003e Sell Its Soul to Win China?\",\"titleNoFormatting\":\"Facebook Inc: Will fb Sell Its Soul to Win China?\",\"location\":\"\",\"publisher\":\"Investorplace.com\",\"publishedDate\":\"Wed, 23 Mar 2016 10:46:54 -0700\",\"signedRedirectUrl\":\"http://news.google.com/news/url?sa\\u003dT\\u0026ct2\\u003dus\\u0026fd\\u003dS\\u0026url\\u003dhttp://investorplace.com/2016/03/facebook-fb-stock-china/\\u0026cid\\u003d52779068060921\\u0026ei\\u003dpJT0VsjkKqbwwQG0pKE4\\u0026usg\\u003dAFQjCNGlgKQWSR6B1hNEObepj_3vSGHFaQ\",\"language\":\"en\",\"image\":{\"url\":\"http://investorplace.com/wp-content/uploads/2016/03/fb-stock-china-chinese-investing.jpg\",\"tbUrl\":\"http://t3.gstatic.com/images?q\\u003dtbn:ANd9GcTvpLHboujdYieSZARYBGQDMpWvG5uRpGfZw8utQVfYw3yEYDgm2YVR8huC\",\"originalContextUrl\":\"http://investorplace.com/2016/03/facebook-fb-stock-china/\",\"publisher\":\"Investorplace.com\",\"tbWidth\":80,\"tbHeight\":53}},{\"GsearchResultClass\":\"GnewsSearch\",\"clusterUrl\":\"\",\"content\":\"I last changed my Facebook profile picture on October 9, 2015, and I honestly don\\u0026#39;t plan on changing it again for a very, very long time. Why, you ask? Because changing your Facebook profile picture is an emotional roller coaster that I honestly have \\u003cb\\u003e...\\u003c/b\\u003e\",\"unescapedUrl\":\"http://elitedaily.com/life/facebook-profile-picture/1432481/\",\"url\":\"http%3A%2F%2Felitedaily.com%2Flife%2Ffacebook-profile-picture%2F1432481%2F\",\"title\":\"56 Thoughts That Go Through Your Head When Changing Your \\u003cb\\u003eFB\\u003c/b\\u003e Profile Picture\",\"titleNoFormatting\":\"56 Thoughts That Go Through Your Head When Changing Your fb Profile Picture\",\"location\":\"\",\"publisher\":\"Elite Daily (blog)\",\"publishedDate\":\"Thu, 24 Mar 2016 10:37:30 -0700\",\"signedRedirectUrl\":\"http://news.google.com/news/url?sa\\u003dT\\u0026ct2\\u003dus\\u0026fd\\u003dS\\u0026url\\u003dhttp://elitedaily.com/life/facebook-profile-picture/1432481/\\u0026cid\\u003d0\\u0026ei\\u003dpJT0VsjkKqbwwQG0pKE4\\u0026usg\\u003dAFQjCNGFNEdkXDePKSfeKEvTXnbdA1on8g\",\"language\":\"en\",\"image\":{\"url\":\"http://cdn29.elitedaily.com/content/uploads/2016/03/24124545/Stocksy_txp1e7837852Et000_Small_405782.jpg\",\"tbUrl\":\"http://t0.gstatic.com/images?q\\u003dtbn:ANd9GcTROZwX3AryorpL2sbvlIsfooDe-9a8cVAecjrnTGZ5nKbL0ANY2Ssw3Z0L\",\"originalContextUrl\":\"http://elitedaily.com/life/facebook-profile-picture/1432481/\",\"publisher\":\"Elite Daily (blog)\",\"tbWidth\":80,\"tbHeight\":40}},{\"GsearchResultClass\":\"GnewsSearch\",\"clusterUrl\":\"\",\"content\":\"facebook video Facebook updated its Page feature to show more statistics on how videos perform, the company announced Thursday. Photo: Reuters/Dado Ruvic. Facebook is finally helping publishers understand when their videos are grabbing the most\\u0026nbsp;...\",\"unescapedUrl\":\"http://www.ibtimes.com/facebook-inc-fb-daily-video-views-get-new-metrics-publishers-2342825\",\"url\":\"http%3A%2F%2Fwww.ibtimes.com%2Ffacebook-inc-fb-daily-video-views-get-new-metrics-publishers-2342825\",\"title\":\"Facebook Inc. (\\u003cb\\u003eFB\\u003c/b\\u003e) Daily Video Views Get New Metrics For Publishers\",\"titleNoFormatting\":\"Facebook Inc. (fb) Daily Video Views Get New Metrics For Publishers\",\"location\":\"\",\"publisher\":\"International Business Times\",\"publishedDate\":\"Thu, 24 Mar 2016 10:02:30 -0700\",\"signedRedirectUrl\":\"http://news.google.com/news/url?sa\\u003dT\\u0026ct2\\u003dus\\u0026fd\\u003dS\\u0026url\\u003dhttp://www.ibtimes.com/facebook-inc-fb-daily-video-views-get-new-metrics-publishers-2342825\\u0026cid\\u003d52779068604598\\u0026ei\\u003dpJT0VsjkKqbwwQG0pKE4\\u0026usg\\u003dAFQjCNEhiFQx09xqVa5NWzAE1nqtmZMKeQ\",\"language\":\"en\",\"image\":{\"url\":\"http://s1.ibtimes.com/sites/www.ibtimes.com/files/styles/md/public/2016/02/18/facebook-video.jpg\",\"tbUrl\":\"http://t0.gstatic.com/images?q\\u003dtbn:ANd9GcR92bU1SNmpVrVdDdpbgn6taYlqH9a_8FHMtgPPgvKSXomCjpMDmOGmlnzo\",\"originalContextUrl\":\"http://www.ibtimes.com/facebook-inc-fb-daily-video-views-get-new-metrics-publishers-2342825\",\"publisher\":\"International Business Times\",\"tbWidth\":80,\"tbHeight\":53}}],\"cursor\":{\"pages\":[{\"start\":\"0\",\"label\":1},{\"start\":\"4\",\"label\":2},{\"start\":\"8\",\"label\":3},{\"start\":\"12\",\"label\":4},{\"start\":\"16\",\"label\":5},{\"start\":\"20\",\"label\":6},{\"start\":\"24\",\"label\":7},{\"start\":\"28\",\"label\":8}],\"estimatedResultCount\":\"72345303\",\"currentPageIndex\":0,\"moreResultsUrl\":\"http://news.google.com/nwshp?oe\\u003dutf8\\u0026ie\\u003dutf8\\u0026source\\u003duds\\u0026q\\u003dFB\\u0026hl\\u003den\\u0026start\\u003d0\"}}, \"responseDetails\": null, \"responseStatus\": 200}";
                stockData = new Gson().fromJson(json, StockNews.class);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return stockData;
    }

    public static void main(String[] args){
        String json = "{\"responseData\": {\"results\":[{\"GsearchResultClass\":\"GnewsSearch\",\"clusterUrl\":\"http://news.google.com/news/story?ncl\\u003ddIQyK2tKjqYbQ8MMP0eBb28toolzM\\u0026hl\\u003den\\u0026ned\\u003dus\",\"content\":\"Kevin Turner, the former NFL fullback with ALS who was a lead plaintiff in a landmark concussion lawsuit against the league, died Thursday morning. He was 46. Turner played for the University of Alabama prior to his eight-years in the NFL that included \\u003cb\\u003e...\\u003c/b\\u003e\",\"unescapedUrl\":\"http://www.nydailynews.com/sports/football/nfl-fb-kevin-turner-dies-46-battle-als-article-1.2576628\",\"url\":\"http%3A%2F%2Fwww.nydailynews.com%2Fsports%2Ffootball%2Fnfl-fb-kevin-turner-dies-46-battle-als-article-1.2576628\",\"title\":\"Former NFL fullback Kevin Turner dies at 46 after battle with ALS\",\"titleNoFormatting\":\"Former NFL fullback Kevin Turner dies at 46 after battle with ALS\",\"location\":\"\",\"publisher\":\"New York Daily News\",\"publishedDate\":\"Thu, 24 Mar 2016 11:51:58 -0700\",\"signedRedirectUrl\":\"http://news.google.com/news/url?sa\\u003dT\\u0026ct2\\u003dus\\u0026fd\\u003dS\\u0026url\\u003dhttp://www.nydailynews.com/sports/football/nfl-fb-kevin-turner-dies-46-battle-als-article-1.2576628\\u0026cid\\u003d52779068622580\\u0026ei\\u003dpJT0VsjkKqbwwQG0pKE4\\u0026usg\\u003dAFQjCNGeIadGJSn_tpl5PnZLkHVbT1AK8A\",\"language\":\"en\",\"image\":{\"url\":\"http://assets.nydailynews.com/polopoly_fs/1.2576633.1458845268!/img/httpImage/image.jpg_gen/derivatives/article_635/nfl-concussion-lawsuits-football.jpg\",\"tbUrl\":\"http://t3.gstatic.com/images?q\\u003dtbn:ANd9GcSXEr-8wbT32SoBsOhzIuPrtcyNpkF-Zg4USfGHDm3_6_lknyf0kXn2uMk\",\"originalContextUrl\":\"http://www.nydailynews.com/sports/football/nfl-fb-kevin-turner-dies-46-battle-als-article-1.2576628\",\"publisher\":\"New York Daily News\",\"tbWidth\":80,\"tbHeight\":53},\"relatedStories\":[{\"unescapedUrl\":\"http://www.csnphilly.com/football-philadelphia-eagles/former-eagles-fb-kevin-turner-dies-46-following-als-battle\",\"url\":\"http%3A%2F%2Fwww.csnphilly.com%2Ffootball-philadelphia-eagles%2Fformer-eagles-fb-kevin-turner-dies-46-following-als-battle\",\"title\":\"Former Eagles \\u003cb\\u003eFB\\u003c/b\\u003e Kevin Turner dies at 46 following ALS battle\",\"titleNoFormatting\":\"Former Eagles fb Kevin Turner dies at 46 following ALS battle\",\"location\":\"\",\"publisher\":\"Comcast SportsNet Philadelphia\",\"publishedDate\":\"Thu, 24 Mar 2016 11:39:08 -0700\",\"signedRedirectUrl\":\"http://news.google.com/news/url?sa\\u003dT\\u0026ct2\\u003dus\\u0026fd\\u003dS\\u0026url\\u003dhttp://www.csnphilly.com/football-philadelphia-eagles/former-eagles-fb-kevin-turner-dies-46-following-als-battle\\u0026cid\\u003d52779068622580\\u0026ei\\u003dpJT0VsjkKqbwwQG0pKE4\\u0026usg\\u003dAFQjCNHl9RK2sueSCrdag5EqwKA3md2VjQ\",\"language\":\"en\"},{\"unescapedUrl\":\"http://sports.yahoo.com/blogs/nfl-shutdown-corner/former-patriots--eagles-fb-kevin-turner-dies-after-battle-with-als-192048010.html\",\"url\":\"http%3A%2F%2Fsports.yahoo.com%2Fblogs%2Fnfl-shutdown-corner%2Fformer-patriots--eagles-fb-kevin-turner-dies-after-battle-with-als-192048010.html\",\"title\":\"Former Patriots, Eagles \\u003cb\\u003eFB\\u003c/b\\u003e Kevin Turner dies after battle with ALS\",\"titleNoFormatting\":\"Former Patriots, Eagles fb Kevin Turner dies after battle with ALS\",\"location\":\"\",\"publisher\":\"Yahoo Sports (blog)\",\"publishedDate\":\"Thu, 24 Mar 2016 12:18:45 -0700\",\"signedRedirectUrl\":\"http://news.google.com/news/url?sa\\u003dT\\u0026ct2\\u003dus\\u0026fd\\u003dS\\u0026url\\u003dhttp://sports.yahoo.com/blogs/nfl-shutdown-corner/former-patriots--eagles-fb-kevin-turner-dies-after-battle-with-als-192048010.html\\u0026cid\\u003d52779068622580\\u0026ei\\u003dpJT0VsjkKqbwwQG0pKE4\\u0026usg\\u003dAFQjCNFmqkfgJ_WXpDA9r0SPUUoARTSzWg\",\"language\":\"en\"},{\"unescapedUrl\":\"http://www.wptz.com/sports/former-nfl-alabama-fb-turner-dies-at-46/38679480\",\"url\":\"http%3A%2F%2Fwww.wptz.com%2Fsports%2Fformer-nfl-alabama-fb-turner-dies-at-46%2F38679480\",\"title\":\"Former NFL, Alabama \\u003cb\\u003eFB\\u003c/b\\u003e Turner dies at 46\",\"titleNoFormatting\":\"Former NFL, Alabama fb Turner dies at 46\",\"location\":\"\",\"publisher\":\"WPTZ\",\"publishedDate\":\"Thu, 24 Mar 2016 14:00:00 -0700\",\"signedRedirectUrl\":\"http://news.google.com/news/url?sa\\u003dT\\u0026ct2\\u003dus\\u0026fd\\u003dS\\u0026url\\u003dhttp://www.wptz.com/sports/former-nfl-alabama-fb-turner-dies-at-46/38679480\\u0026cid\\u003d52779068622580\\u0026ei\\u003dpJT0VsjkKqbwwQG0pKE4\\u0026usg\\u003dAFQjCNG-pK7LpPDw5vyvZnB_kIqiAvTORQ\",\"language\":\"en\"},{\"unescapedUrl\":\"http://247sports.com/Bolt/Former-Eagles-fb-Kevin-Turner-passes-away-44453950\",\"url\":\"http%3A%2F%2F247sports.com%2FBolt%2FFormer-Eagles-fb-Kevin-Turner-passes-away-44453950\",\"title\":\"Kevin Turner passes away after battle with ALS\",\"titleNoFormatting\":\"Kevin Turner passes away after battle with ALS\",\"location\":\"\",\"publisher\":\"247Sports\",\"publishedDate\":\"Thu, 24 Mar 2016 12:52:30 -0700\",\"signedRedirectUrl\":\"http://news.google.com/news/url?sa\\u003dT\\u0026ct2\\u003dus\\u0026fd\\u003dS\\u0026url\\u003dhttp://247sports.com/Bolt/Former-Eagles-fb-Kevin-Turner-passes-away-44453950\\u0026cid\\u003d52779068622580\\u0026ei\\u003dpJT0VsjkKqbwwQG0pKE4\\u0026usg\\u003dAFQjCNEy1phBdz0n10_QbW5LTemIMyekwA\",\"language\":\"en\"},{\"unescapedUrl\":\"http://www.csnne.com/new-england-patriots/former-patriots-fb-turner-dies-after-battle-als\",\"url\":\"http%3A%2F%2Fwww.csnne.com%2Fnew-england-patriots%2Fformer-patriots-fb-turner-dies-after-battle-als\",\"title\":\"Life and death of Turner carries meaning, poignancy\",\"titleNoFormatting\":\"Life and death of Turner carries meaning, poignancy\",\"location\":\"\",\"publisher\":\"Comcast SportsNet New England\",\"publishedDate\":\"Thu, 24 Mar 2016 13:33:09 -0700\",\"signedRedirectUrl\":\"http://news.google.com/news/url?sa\\u003dT\\u0026ct2\\u003dus\\u0026fd\\u003dS\\u0026url\\u003dhttp://www.csnne.com/new-england-patriots/former-patriots-fb-turner-dies-after-battle-als\\u0026cid\\u003d52779068622580\\u0026ei\\u003dpJT0VsjkKqbwwQG0pKE4\\u0026usg\\u003dAFQjCNEdG4LGYVxJW9ZGS9GzQ87GTMduIw\",\"language\":\"en\"}]},{\"GsearchResultClass\":\"GnewsSearch\",\"clusterUrl\":\"\",\"content\":\"Sure, \\u003cb\\u003eFB\\u003c/b\\u003e is still growing users at a faster pace than Twitter Inc (TWTR), which is extremely impressive since Facebook is five times larger than Twitter. Decelerating user growth, however, seems inevitable at this point; there are only so many people \\u003cb\\u003e...\\u003c/b\\u003e\",\"unescapedUrl\":\"http://investorplace.com/2016/03/facebook-fb-stock-china/\",\"url\":\"http%3A%2F%2Finvestorplace.com%2F2016%2F03%2Ffacebook-fb-stock-china%2F\",\"title\":\"Facebook Inc: Will \\u003cb\\u003eFB\\u003c/b\\u003e Sell Its Soul to Win China?\",\"titleNoFormatting\":\"Facebook Inc: Will fb Sell Its Soul to Win China?\",\"location\":\"\",\"publisher\":\"Investorplace.com\",\"publishedDate\":\"Wed, 23 Mar 2016 10:46:54 -0700\",\"signedRedirectUrl\":\"http://news.google.com/news/url?sa\\u003dT\\u0026ct2\\u003dus\\u0026fd\\u003dS\\u0026url\\u003dhttp://investorplace.com/2016/03/facebook-fb-stock-china/\\u0026cid\\u003d52779068060921\\u0026ei\\u003dpJT0VsjkKqbwwQG0pKE4\\u0026usg\\u003dAFQjCNGlgKQWSR6B1hNEObepj_3vSGHFaQ\",\"language\":\"en\",\"image\":{\"url\":\"http://investorplace.com/wp-content/uploads/2016/03/fb-stock-china-chinese-investing.jpg\",\"tbUrl\":\"http://t3.gstatic.com/images?q\\u003dtbn:ANd9GcTvpLHboujdYieSZARYBGQDMpWvG5uRpGfZw8utQVfYw3yEYDgm2YVR8huC\",\"originalContextUrl\":\"http://investorplace.com/2016/03/facebook-fb-stock-china/\",\"publisher\":\"Investorplace.com\",\"tbWidth\":80,\"tbHeight\":53}},{\"GsearchResultClass\":\"GnewsSearch\",\"clusterUrl\":\"\",\"content\":\"I last changed my Facebook profile picture on October 9, 2015, and I honestly don\\u0026#39;t plan on changing it again for a very, very long time. Why, you ask? Because changing your Facebook profile picture is an emotional roller coaster that I honestly have \\u003cb\\u003e...\\u003c/b\\u003e\",\"unescapedUrl\":\"http://elitedaily.com/life/facebook-profile-picture/1432481/\",\"url\":\"http%3A%2F%2Felitedaily.com%2Flife%2Ffacebook-profile-picture%2F1432481%2F\",\"title\":\"56 Thoughts That Go Through Your Head When Changing Your \\u003cb\\u003eFB\\u003c/b\\u003e Profile Picture\",\"titleNoFormatting\":\"56 Thoughts That Go Through Your Head When Changing Your fb Profile Picture\",\"location\":\"\",\"publisher\":\"Elite Daily (blog)\",\"publishedDate\":\"Thu, 24 Mar 2016 10:37:30 -0700\",\"signedRedirectUrl\":\"http://news.google.com/news/url?sa\\u003dT\\u0026ct2\\u003dus\\u0026fd\\u003dS\\u0026url\\u003dhttp://elitedaily.com/life/facebook-profile-picture/1432481/\\u0026cid\\u003d0\\u0026ei\\u003dpJT0VsjkKqbwwQG0pKE4\\u0026usg\\u003dAFQjCNGFNEdkXDePKSfeKEvTXnbdA1on8g\",\"language\":\"en\",\"image\":{\"url\":\"http://cdn29.elitedaily.com/content/uploads/2016/03/24124545/Stocksy_txp1e7837852Et000_Small_405782.jpg\",\"tbUrl\":\"http://t0.gstatic.com/images?q\\u003dtbn:ANd9GcTROZwX3AryorpL2sbvlIsfooDe-9a8cVAecjrnTGZ5nKbL0ANY2Ssw3Z0L\",\"originalContextUrl\":\"http://elitedaily.com/life/facebook-profile-picture/1432481/\",\"publisher\":\"Elite Daily (blog)\",\"tbWidth\":80,\"tbHeight\":40}},{\"GsearchResultClass\":\"GnewsSearch\",\"clusterUrl\":\"\",\"content\":\"facebook video Facebook updated its Page feature to show more statistics on how videos perform, the company announced Thursday. Photo: Reuters/Dado Ruvic. Facebook is finally helping publishers understand when their videos are grabbing the most\\u0026nbsp;...\",\"unescapedUrl\":\"http://www.ibtimes.com/facebook-inc-fb-daily-video-views-get-new-metrics-publishers-2342825\",\"url\":\"http%3A%2F%2Fwww.ibtimes.com%2Ffacebook-inc-fb-daily-video-views-get-new-metrics-publishers-2342825\",\"title\":\"Facebook Inc. (\\u003cb\\u003eFB\\u003c/b\\u003e) Daily Video Views Get New Metrics For Publishers\",\"titleNoFormatting\":\"Facebook Inc. (fb) Daily Video Views Get New Metrics For Publishers\",\"location\":\"\",\"publisher\":\"International Business Times\",\"publishedDate\":\"Thu, 24 Mar 2016 10:02:30 -0700\",\"signedRedirectUrl\":\"http://news.google.com/news/url?sa\\u003dT\\u0026ct2\\u003dus\\u0026fd\\u003dS\\u0026url\\u003dhttp://www.ibtimes.com/facebook-inc-fb-daily-video-views-get-new-metrics-publishers-2342825\\u0026cid\\u003d52779068604598\\u0026ei\\u003dpJT0VsjkKqbwwQG0pKE4\\u0026usg\\u003dAFQjCNEhiFQx09xqVa5NWzAE1nqtmZMKeQ\",\"language\":\"en\",\"image\":{\"url\":\"http://s1.ibtimes.com/sites/www.ibtimes.com/files/styles/md/public/2016/02/18/facebook-video.jpg\",\"tbUrl\":\"http://t0.gstatic.com/images?q\\u003dtbn:ANd9GcR92bU1SNmpVrVdDdpbgn6taYlqH9a_8FHMtgPPgvKSXomCjpMDmOGmlnzo\",\"originalContextUrl\":\"http://www.ibtimes.com/facebook-inc-fb-daily-video-views-get-new-metrics-publishers-2342825\",\"publisher\":\"International Business Times\",\"tbWidth\":80,\"tbHeight\":53}}],\"cursor\":{\"pages\":[{\"start\":\"0\",\"label\":1},{\"start\":\"4\",\"label\":2},{\"start\":\"8\",\"label\":3},{\"start\":\"12\",\"label\":4},{\"start\":\"16\",\"label\":5},{\"start\":\"20\",\"label\":6},{\"start\":\"24\",\"label\":7},{\"start\":\"28\",\"label\":8}],\"estimatedResultCount\":\"72345303\",\"currentPageIndex\":0,\"moreResultsUrl\":\"http://news.google.com/nwshp?oe\\u003dutf8\\u0026ie\\u003dutf8\\u0026source\\u003duds\\u0026q\\u003dFB\\u0026hl\\u003den\\u0026start\\u003d0\"}}, \"responseDetails\": null, \"responseStatus\": 200}";
        StockNews stockData = new Gson().fromJson(json, StockNews.class);
    }
}
