;;(doctype :xhtml-transitional)
[:html
 {:xmlns "http://www.w3.org/1999/xhtml", :lang "en", :xml:lang "en"}
 [:head
  [:meta
   {:http-equiv "content-type", :content "text/html; charset=UTF-8"}]
  [:meta {:name "description", :content (:description metadata)}]
  [:meta {:name "keywords", :content (:tags metadata)}]
  [:meta {:name "author", :content (:site-author (static.config/config))}]
  ;; [:link {:rel "icon",
  ;;         :href "/images/favicon.ico" :type "image/x-icon"}]
  ;; [:link {:rel "shortcut icon",
  ;;         :href "/images/favicon.ico" :type "image/x-icon"}]
  [:link {:rel "stylesheet", :type "text/css", :href "/default.css"}]
  [:link
   {:rel "alternate", :type "application/rss+xml",
    :title (:site-title (static.config/config)), :href "/rss-feed"}]

  (if (= (:type metadata) :post)
    [:link {:rel "canonical"
	    :href (str (:site-url (static.config/config)) (:url metadata))}])
  [:title (:title metadata)]]
 [:body
  [:div {:id "header"}
   [:h1 {:class "site-name"}
    [:a {:href "/"} "Bytopia"]]
   [:p {:class "site-description"} (:site-description (static.config/config))]]
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
       [:input {:type "hidden" :name "sitesearch"
                :value "bytopia.org"}]
       [:input {:id "searchsubmit", :value "Search", :type "submit"}]]]]
    [:div {:class "clear"}]
    [:div {:id "main"}
     [:div {:class "post"}
      (if (and (or (= (:type metadata) :post)
                   (= (:type metadata) :site))
               (not (:skip-title metadata)))
        [:h2 {:class "entry-title", :margin-bottom "5px"} (:title metadata)])

      (if (= (:type metadata) :post)
        [:div {:class "entry-info"}
         [:span {:class "entry-date"}
          (:date metadata)]
         (reduce
          (fn[h v]
            (conj h [:a {:href (str "/tags/#" v)} v] " "))
          [:span {:class "entry-tags"} "Tags: "]
          (.split (:tags metadata) " "))
         [:div {:class "clear"}]])

      content

      (when (= (:type metadata) :post)
        [:div {:id "comments"}
         [:script {:src "/juvia.js", :type "text/javascript"}]])]]

    [:div {:id "sidebar"}
     [:div {}
      [:h2 {:class "title"} "Recent posts"]
      [:ul {:id "recent-posts"}
       (for [f (take 5 (reverse (static.io/list-files :posts)))
             :let [[metadata _] (static.io/read-doc f)]]
         [:li [:a {:href (static.core/post-url f)} (:title metadata)]])]]
     [:div {}
      [:h2 {:class "title"} "Tags"]
      (reduce (fn [div tag-kw]
                (conj div [:a {:href (str "/tags/#" (name tag-kw))}
                           (name tag-kw)] " "))
              [:div {:id "taglist"}]
              (keys (static.core/tag-map)))]]]]
  [:div {:id "footer"}
   [:div {:id "footerbox"}
    ;; [:a {:href "/rss-feed"} " RSS Feed"]
    [:div {:class "support"}
     "&copy; " (static.core/blog-timespan) " Alexander Yakushev"
     [:p ;; {:class "support"}
      "Powered by " [:a {:href "http://nakkaya.com/static.html"} "Static"]
      " | Theme " [:a {:href "http://axiu.me"} "mxs"]]]]]
  ]]



