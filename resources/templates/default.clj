(let [config (config)
      blog-timespan (let [created (:date-created config)
                          now (+ (.getYear (java.util.Date.)) 1900)]
                      (if (= created now)
                        (str created)
                        (str created "-" now)))]
  [:html
   {:xmlns "http://www.w3.org/1999/xhtml", :lang "en", :xml:lang "en"}
   [:head
    [:meta {:http-equiv "content-type", :content "text/html; charset=UTF-8"}]
    [:meta {:name "description", :content (:description metadata)}]
    [:meta {:name "keywords", :content (apply str (interpose "," (:tags metadata)))}]
    [:meta {:name "author", :content (:site-author config)}]
    [:link {:rel "icon", :href "/images/favicon.ico" :type "image/x-icon"}]
    [:link {:rel "shortcut icon",:href "/images/favicon.ico" :type "image/x-icon"}]
    [:link {:rel "stylesheet", :type "text/css", :href "/default.css"}]
    [:link {:rel "stylesheet", :type "text/css", :href "/tomorrow_night.css"}]
    (when (= (:title metadata) "Home")
      [:link {:rel "stylesheet", :type "text/css", :href "/goodreads.css"}])
    [:link
     {:rel "alternate", :type "application/rss+xml",
      :title (:site-title config), :href "/rss-feed"}]

    (if (= (:type metadata) :post)
      [:link {:rel "canonical"
              :href (str (:site-url config) (:url metadata))}])
    [:title (str (:title metadata) " - " (:site-title config))]]
   [:body
    [:div {:id "header"}
     [:h1 {:class "site-name"}
      [:a {:href "/"} "Bytopia"]]
     [:p {:class "site-description"} (:site-description config)]]
    [:div {:class "clear"}]
    [:div {:id "content"}
     [:div {:id "outbox"}
      [:div {:id "navigation"}
       [:div {:id "pages"}
        (let [make-link (fn [link-name & {:keys [link title]
                                         :or {link (str "/" (.toLowerCase link-name) ".html")
                                              title link-name}}]
                          [:li (if (and (= (:type metadata) :site)
                                        (= (:title metadata) title))
                                 {:class "current_page_item"} {})
                           [:a {:href link} link-name]])]
          [:ul
           (make-link "Home" :link "/")
           (make-link "About")
           (make-link "Projects")
           (make-link "Tags" :link "/tags/")
           (make-link "Archives")])
        [:div {:class "clear"}]]
       [:form {:action "http://www.google.com/search",
               :id "searchform", :method "get"}
        [:div
         [:label {:class "screen-reader-text", :for "s"} "Search for:"]
         [:input {:id "s", :name "q", :value "", :type "text"}]
         [:input {:type "hidden" :name "sitesearch", :value "www.bytopia.org"}]
         [:input {:id "searchsubmit", :value "Search", :type "submit"}]]]]
      [:div {:class "clear"}]
      [:div {:id "main"}
       [:div {:class "post"}
        (if (and (or (= (:type metadata) :post)
                     (= (:type metadata) :site))
                 (not (:skip-title metadata)))
          [:h2 {:class "entry-title"} (:title metadata)])

        (if (= (:type metadata) :post)
          [:div {:class "entry-info"}
           [:span {:class "entry-date"}
            (format-date (:date metadata) "dd MMMM YYYY")]
           (reduce
            (fn[h v]
              (conj h [:a {:href (str "/tags/#" v)} v] " "))
            [:span {:class "entry-tags"} "Tags: "]
            (:tags metadata))
           [:div {:class "clear"}]])

        content

        (when (= (:type metadata) :post)
          [:div {:id "comments"}
           [:script {:src "/juvia.js", :type "text/javascript"}]])]]

      [:div {:id "sidebar"}
       [:div {}
        [:h2 {:class "title"} "Recent posts"]
        [:ul {:id "recent-posts"}
         (for [f (take 5 (reverse (io/list-files :posts)))
               :let [[metadata _] (io/read-doc f)]]
           [:li [:a {:href (post-url f)} (:title metadata)]])]]
       [:div {}
        [:h2 {:class "title"} "Tags"]
        [:ul {}
         (reduce (fn [div tag-kw]
                   (conj div [:a {:href (str "/tags/#" (name tag-kw))}
                              (name tag-kw)] " "))
                 [:li {:id "taglist"}]
                 (keys (tag-map)))
         ]]
       [:div {:id "links"}
        [:h2 {:class "title"} "Links"]
        [:div {:id "links-left"}
         [:ul {}
          [:li {} [:a {:id "rss-feed" :href "/rss-feed"} "RSS Feed"]]
          [:li {} [:a {:id "github" :href "https://github.com/alexander-yakushev"} "Github"]]
          [:li {} [:a {:id "gplus" :href "https://plus.google.com/u/0/107472102450770404696"} "Google+"]]]]
        [:div {:id "links-right"}
         [:ul {}
          [:li {} [:a {:id "email" :href "mailto:alex@bytopia.org"} "E-mail"]]
          [:li {} [:a {:id "linkedin" :href "https://www.linkedin.com/profile/view?id=82917611"} "LinkedIn"]]
          [:li {} [:a {:id "goodreads" :href "https://www.goodreads.com/user/show/5694609-alexander-yakushev"} "Goodreads"]]]]
        [:div {:class "clear"}]]
       (when (= (:title metadata) "Home")
         [:div {:id "widgets"}
          [:div {:id "goodreads"}
           [:script {:type "text/javascript", :charset "utf-8"
                     :src "https://www.goodreads.com/review/custom_widget/5694609.Recent%20books?cover_position=left&cover_size=small&num_books=5&order=d&shelf=read&show_author=1&show_cover=1&show_rating=0&show_review=0&show_tags=1&show_title=1&sort=date_read&widget_bg_color=FFFFFF&widget_bg_transparent=&widget_border_width=1&widget_id=13821383570&widget_text_color=000000&widget_title_size=medium&widget_width=medium"}]]
          [:div {:class "clear"}]
          [:h2 {:class "title"} "Featured tracks"]
          [:div {:style "margin: 10px;"}
           [:iframe {:id "widget", :scrolling "no", :frameborder 0, :width 293, :height 470,
                     :style "width: 293px; height: 470px;"
                     :src "//widgets.jamendo.com/v3/playlist/89122293?autoplay=0&layout=cover&manualWidth=293&width=293&theme=light&highlight=7&tracklist=true&tracklist_n=3&embedCode="}]]])
       ]]]
    [:div {:id "footer"}
     [:div {:id "footerbox"}
      [:div {:class "support"}
       "&copy; " blog-timespan " Alexander Yakushev"
       [:p ;; {:class "support"}
        "Powered by " [:a {:href "https://github.com/alexander-yakushev/discharge"} "Discharge"]
        " | Theme " [:a {:href "http://axiu.me"} "mxs"]]]]]

    "<!-- Piwik -->
<script type=\"text/javascript\">
  var _paq = _paq || [];
  _paq.push([\"trackPageView\"]);
  _paq.push([\"enableLinkTracking\"]);

  (function() {
    var u=\"http://analytics.bytopia.org/\";
    _paq.push([\"setTrackerUrl\", u+\"piwik.php\"]);
    _paq.push([\"setSiteId\", \"1\"]);
    var d=document, g=d.createElement(\"script\"), s=d.getElementsByTagName(\"script\")[0]; g.type=\"text/javascript\";
    g.defer=true; g.async=true; g.src=u+\"piwik.js\"; s.parentNode.insertBefore(g,s);
  })();
</script>
<!-- End Piwik Code -->"
    ;; [:script {:src "/piwik.js" :type "text/javascript"}]
    ;; [:noscript [:p [:img {:src "http://analytics.bytopia.org/piwik.php?idsite=1",
    ;;                       :style "border:0", :alt ""}]]]
    ]])
