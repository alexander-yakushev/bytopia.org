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
 :create-tags true
 :rss-for-tags ["clojure"]

 :rsync ["rsync" "--delete" "--checksum" "--recursive" "-avz" "-og" "--chmod=a+rX"]
 :host "do-core"
 :user "core"
 :deploy-dir "~/services/bytopia.org/"

 :emacs "/usr/bin/emacs"
 :emacs-eval [(require 'org) (setq org-export-with-section-numbers nil)]]
