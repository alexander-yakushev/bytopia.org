[:site-title "Bytopia.org"
 :site-description "Inventing, implementing, living a life."
 :site-url "http://www.bytopia.org"
 :site-author "Alexander Yakushev"
 :date-created 2012
 :in-dir "resources/"
 :out-dir "target/www/"
 :default-template "default.clj"
 :encoding "UTF-8"
 :blog-as-index false
 :create-archives false
 :create-tags false
 :rss-for-tags ["clojure"]

 :rsync "rsync"
 :host "digitalocean-bytopia"
 :user "unlogic"
 :deploy-dir "/home/www-data/"
 :post-deploy-cmd ["ssh" "digitalocean-bytopia" "/home/www-data/deploy-www"]

 :emacs "/usr/bin/emacs"
 :emacs-eval [(require 'org)]]
